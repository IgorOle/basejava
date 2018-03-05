package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;
import static org.junit.Assert.assertTrue;
import java.io.File;

public class FileStorageTest extends AbstractStorageTest{

    public FileStorageTest() {
        super(new FileStorage(new File("D:/igo/storageDir")));
    }

    @Override
    public void get() throws Exception {
        assertTrue (storage.get("uuid1").equals(r1));
    }

    @Override
    public void update() throws Exception {
        Resume resumeBefore = r1;
        storage.update(r1);
        assertTrue(storage.get("uuid1").equals(resumeBefore));
    }
}