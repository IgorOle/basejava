package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.exception.NotExistStorageException;
import com.igorole.basejava.webapp.model.*;
import com.igorole.basejava.webapp.sql.SqlHelper;
import com.igorole.basejava.webapp.util.JsonParser;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

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
        return sqlHelper.transactionalExecute(conn -> {
            Resume r = null;
            try (PreparedStatement ps = conn.prepareStatement(" SELECT * FROM resume r " +
                    "    LEFT JOIN contact c " +
                    "        ON r.uuid = c.resume_uuid " +
                    "    WHERE r.uuid = ? ")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                do {
                    if (r == null)
                        r = new Resume(uuid, rs.getString("full_name"));
                    addContacts(r, rs);
                } while (rs.next());
            }
            try (PreparedStatement ps = conn.prepareStatement("select * from section where resume_uuid=?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(r, rs);
                }
            }
            return r;
        });
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
            try (PreparedStatement ps = conn.prepareStatement("delete from section where resume_uuid=?")) {
                ps.setString(1, r.getUuid());
                ps.execute();
            }
            saveSection(conn, r);
            saveContacts(conn, r);
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
            saveContacts(conn, r);
            saveSection(conn, r);
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
        return sqlHelper.transactionalExecute(conn -> {
            ArrayList<Resume> list;
            Map<String, Resume> resumes = new HashMap<>();

            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    resumes.put(rs.getString("uuid"), new Resume(rs.getString("uuid").trim(), rs.getString("full_name")));
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume r = resumes.get(rs.getString("resume_uuid"));
                    addContacts(r, rs);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume r = resumes.get(rs.getString("resume_uuid"));
                    addSection(r, rs);
                }
            }
            list = new ArrayList<>(resumes.values());
            Collections.sort(list);
            return list;
        });
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

    //add sections to resume FROM DB
    private void addSection(Resume r, ResultSet rs) throws SQLException {
        String values = rs.getString("value");
        SectionType type = SectionType.valueOf(rs.getString("type"));
        Section section = null;
        switch (type) {
            case PERSONAL:
            case OBJECTIVE:
                section = new TextSection(values);
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                section = new ListSection(Arrays.asList(values.split("\n")));
                break;
//            case EXPERIENCE:
//            case EDUCATION:
//                writeOrganizationSection(dos, section);
//                break;
        }
        if (section != null) {
            r.addSections(type, section);
        }
    }

    //save sections INTO DB
    private void saveSection(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, Section> e : r.getSections().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                switch (e.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        ps.setString(3, e.getValue().toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ps.setString(3, ((ListSection)e.getValue()).getItems().stream().map(Object::toString).collect(Collectors.joining("\n")));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        ps.setString(3, JsonParser.write(e.getValue(), Section.class));
                        break;
                }
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    //add contact to resume FROM DB
    private void addContacts(Resume r, ResultSet rs) throws SQLException {
        String value = rs.getString("value");
        if (value != null)
            r.addContact(ContactType.valueOf(rs.getString("type")), value);
    }

    //save contacts INTO DB
    private void saveContacts(Connection conn, Resume r) throws SQLException {
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