package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.exception.ExistStorageException;
import com.igorole.basejava.webapp.exception.NotExistStorageException;
import com.igorole.basejava.webapp.model.Resume;
import org.junit.*;

import static org.junit.Assert.*;

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

    @Test
    public void update() throws Exception {
        storage.update(r1);
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
    public void getAll() throws Exception {
        Resume[] resultArr = storage.getAll();
        Resume[] expectedArr = new Resume[] {r1, r2, r3};
        assertArrayEquals(expectedArr, resultArr);
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