package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.Config;
import com.igorole.basejava.webapp.GenerateResume;
import com.igorole.basejava.webapp.exception.ExistStorageException;
import com.igorole.basejava.webapp.exception.NotExistStorageException;
import com.igorole.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class    AbstractStorageTest {
    protected static final File STORAGE_DIR = com.igorole.basejava.webapp.Config.get().getStorageDir();
    Storage storage;
    Resume r1, r2, r3, r4;
    public final int countElemets = 4;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void init() {
        storage.clear();
        r1 = new Resume("uuid1", "FullName1");
        r2 = new Resume("uuid1", "FullName2");
        r3 = new Resume("uuid1", "FullName3");
        r4 = new Resume("uuid1", "FullName4");
        saveStorage();
    }

    @Test
    public void get() throws Exception {
        assertTrue(r1.equals(storage.get("uuid1")));
    }

    @Test(expected = ExistStorageException.class)
    public void save() {
        storage.save(r1);
    }

    @Test
    public void update() throws Exception {
        Resume resumeBefore = r1;
        storage.update(r1);
        assertTrue(resumeBefore.equals(storage.get("uuid1")));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws IOException {
        storage.delete(r1.getUuid());
        System.out.println(storage.size());
        assertTrue(countElemets - 1 == storage.size());
        storage.get("uuid1");
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getAllSorted() {
        List<Resume> resultArr = storage.getAllSorted();
        List<Resume> expectedArr = Arrays.asList(r1, r2, r4, r3);
        Assert.assertEquals(expectedArr, resultArr);
    }

    @Test
    public void size() {
        assertEquals(countElemets, storage.size());
    }

    private void saveStorage() {
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
        storage.save(r4);
    }
}