package com.igorole.basejava.webapp.sql;


import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Executor<T> {
    T exec(PreparedStatement ps) throws SQLException;
}
