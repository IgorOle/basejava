package com.igorole.basejava.webapp.sql;

import com.igorole.basejava.webapp.exception.ExistStorageException;
import com.igorole.basejava.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    public final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T execute(String sql, Executor<T> executor){
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return executor.exec(ps);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                    throw new ExistStorageException(e.getMessage());
            }
            throw new StorageException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
