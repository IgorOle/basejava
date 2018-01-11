package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public abstract class AbstractArrayStorageTest {
    Storage storage;
    Resume r1, r2, r3;
    int cntEl = 3;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void init() {
        r1 = new Resume("uuid1");
        r2 = new Resume("uuid2");
        r3 = new Resume("uuid3");
    }

    @Test
    public void get() throws Exception {
        saveStorage();
        assertSame(r1, storage.get("uuid1"));
    }

    @Test
    public void save() throws Exception {
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    public void update() throws Exception {
        saveStorage();
        storage.update(r1);
    }

    @Test()
    public void delete() throws Exception {
        int before;
        int after;
        saveStorage();
        before = storage.size();
        storage.delete(r1.getUuid());
        after = storage.size();
        assertTrue((before-1) == after);
    }

    @Test
    public void clear() throws Exception {
        saveStorage();
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getAll() throws Exception {
        saveStorage();
        storage.getAll();
    }

    @Test
    public void size() throws Exception {
        saveStorage();
        assertEquals(cntEl, storage.size());
    }

    private void saveStorage() {
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

}