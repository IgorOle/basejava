package com.igorole.basejava.webapp.sql;


import java.sql.PreparedStatement;

public interface Executor<T> {
    T exec(PreparedStatement ps) throws Exception;
}
