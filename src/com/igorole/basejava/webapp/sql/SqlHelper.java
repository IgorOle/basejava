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

    public <T> T execute(String sql, Executor executor){
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return (T) executor.exec(ps);
        } catch (SQLException e) {
            switch (e.getSQLState()) {
                case "23505":
                    throw new ExistStorageException(e.getMessage());

            }
//            System.out.println("" + e.getErrorCode() + e.getSQLState()+ "/ " + e.getMessage());

//            throw new NotExistStorageException(e.getMessage());
            throw new StorageException(e);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
