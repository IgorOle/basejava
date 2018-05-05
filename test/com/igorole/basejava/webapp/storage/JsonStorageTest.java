package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;
import com.igorole.basejava.webapp.storage.serializer.JsonStreamSerializer;

import static org.junit.Assert.assertTrue;

public class JsonStorageTest extends AbstractStorageTest {

    public JsonStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new JsonStreamSerializer()));
    }

    @Override
    public void get() throws Exception {
        Resume r = storage.get("uuid2");
        assertTrue(storage.get("uuid2").equals(r2));
    }

    @Override
    public void update() throws Exception {
        Resume resumeBefore = r1;
        storage.update(r1);
        assertTrue(storage.get("uuid1").equals(resumeBefore));
    }
}