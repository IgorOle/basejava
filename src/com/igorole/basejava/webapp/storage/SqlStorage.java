package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.exception.NotExistStorageException;
import com.igorole.basejava.webapp.model.ContactType;
import com.igorole.basejava.webapp.model.Resume;
import com.igorole.basejava.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("delete from resume", PreparedStatement::execute);
    }

    @Override
    public Resume get(String uuid) {
        if (getResumes(uuid).size() == 0)
            throw new NotExistStorageException(uuid);
        return getResumes(uuid).get(0);
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("update resume set full_name=? where uuid=?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(r.getUuid());
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("delete from contact where resume_uuid=?")) {
                ps.setString(1, r.getUuid());
                ps.execute();
            }
            addContacts(conn, r);
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {

            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                    ps.setString(1, r.getUuid());
                    ps.setString(2, r.getFullName());
                    ps.execute();
            }
            addContacts(conn, r);
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("delete from resume where uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return getResumes(null);
    }

    @Override
    public int size() {
        return sqlHelper.execute("select count(*) as cnt from resume ", ps -> {
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return 0;
            }
            return rs.getInt("cnt");
        });
    }

    private List<Resume> getResumes(final String uuid) {
        String sql = "" +
                " SELECT * FROM resume r " +
                "    LEFT JOIN contact c " +
                "        ON r.uuid = c.resume_uuid " +
                ((uuid == null) ? "" : "    WHERE r.uuid = ? ") +
                " ORDER BY r.uuid";

        return sqlHelper.execute(sql,
                ps -> {
                    ArrayList<Resume> list = new ArrayList<>();
                    if (uuid != null)
                        ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    String prevUUID = "";
                    while (rs.next()) {
                        if (!prevUUID.equals(rs.getString("uuid").trim())) {
                            prevUUID = rs.getString("uuid").trim();
                            list.add(new Resume(prevUUID, rs.getString("full_name")));
                        }
                        list.get(list.size() - 1).addContact(ContactType.valueOf(rs.getString("type"))
                                , rs.getString("value"));
                    }
                    return list;
                });
    }

    private void addContacts(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}