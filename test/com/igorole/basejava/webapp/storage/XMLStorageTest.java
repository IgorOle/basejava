package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;
import com.igorole.basejava.webapp.storage.serializer.FileStream;
import com.igorole.basejava.webapp.storage.serializer.XMLStream;

import static org.junit.Assert.assertTrue;

public class XMLStorageTest extends AbstractStorageTest {

    public XMLStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new XMLStream()));
    }

    @Override
    public void get() throws Exception {
        assertTrue(storage.get("uuid1").equals(r1));
    }

    @Override
    public void update() throws Exception {
        Resume resumeBefore = r1;
        storage.update(r1);
        assertTrue(storage.get("uuid1").equals(resumeBefore));
    }
}