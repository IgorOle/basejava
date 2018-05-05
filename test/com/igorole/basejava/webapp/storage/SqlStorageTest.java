package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.Config;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(new SqlStorage(Config.get().getUrl(), Config.get().getUser(), Config.get().getPassword()));
    }

}