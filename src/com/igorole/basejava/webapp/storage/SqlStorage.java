package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.exception.NotExistStorageException;
import com.igorole.basejava.webapp.model.Resume;
import com.igorole.basejava.webapp.sql.ConnectionFactory;
import com.igorole.basejava.webapp.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;
    private SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        sqlHelper = new SqlHelper(connectionFactory);
    }

    @Override
    public void clear() {
        sqlHelper.execute("delete from resume", ps -> ps.execute());
    }

    @Override
    public Resume get(String uuid) {
        Resume r = null;
        r = sqlHelper.execute("select * from resume where uuid=?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
        return r;
    }

    @Override
    public void update(Resume r) {
        int res = sqlHelper.execute("update resume set full_name=? where uuid=?", ps -> {
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getUuid());
            return ps.executeUpdate();
        });
        if (res == 0) {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    @Override
    public void save(Resume r) {
        sqlHelper.execute("insert into resume(uuid, full_name) values (?, ?)", ps -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            return ps.execute();
        });
    }

    @Override
    public void delete(String uuid) {
        int res = sqlHelper.execute("delete from resume where uuid = ?", ps -> {
            ps.setString(1, uuid);
            return ps.executeUpdate();
        });
        if (res == 0) {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("select uuid, full_name from resume order by uuid", ps -> {
            ArrayList list = new ArrayList();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            return list;
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("select count(*) as cnt from resume ", ps -> {
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("cnt");
        });
    }
}