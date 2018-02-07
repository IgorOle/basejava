package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.exception.ExistStorageException;
import com.igorole.basejava.webapp.exception.NotExistStorageException;
import com.igorole.basejava.webapp.exception.StorageException;
import com.igorole.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public abstract class AbstractArrayStorageTest {
    Storage storage;
    Resume r1, r2, r3;
    private final int countElemets = 3;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void init() {
        r1 = new Resume("uuid1");
        r2 = new Resume("uuid2");
        r3 = new Resume("uuid3");
        saveStorage();
    }

    @Test
    public void get() throws Exception {
        assertSame(r1, storage.get("uuid1"));
    }

    @Test(expected = ExistStorageException.class)
    public void save() throws Exception {
        storage.save(r1);
    }

    @Test(expected = StorageException.class)
    public void saveOverFlow() {
        assumeTrue(storage instanceof AbstractArrayStorage);
        int countResume = storage.size();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT - countResume; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException ex) {
            Assert.fail();
        }
        storage.save(new Resume());
    }

    @Test
    public void update() throws Exception {
        Resume resumeBefore = r1;
        storage.update(r1);
        assertTrue(resumeBefore == storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(r1.getUuid());
        assertTrue(2 == storage.size());
        storage.get("uuid1");
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> resultArr = storage.getAllSorted();
        List<Resume> expectedArr = Arrays.asList(r1, r2, r3);
        Assert.assertEquals(expectedArr, resultArr);
    }

    @Test
    public void size() throws Exception {
        assertEquals(countElemets, storage.size());
    }

    private void saveStorage() {
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }
}