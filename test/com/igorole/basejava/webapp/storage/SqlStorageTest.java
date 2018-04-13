package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.Config;
import com.igorole.basejava.webapp.model.Resume;
import com.igorole.basejava.webapp.storage.serializer.JsonStreamSerializer;

import static org.junit.Assert.assertTrue;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(new SqlStorage(Config.get().getUrl(), Config.get().getUser(), Config.get().getPassword()));
    }

}