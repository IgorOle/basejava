package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.GenerateResume;
import com.igorole.basejava.webapp.exception.ExistStorageException;
import com.igorole.basejava.webapp.exception.NotExistStorageException;
import com.igorole.basejava.webapp.model.Resume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AbstractStorageTest {
    protected static final File STORAGE_DIR = com.igorole.basejava.webapp.Config.get().getStorageDir();
    Storage storage;
    Resume r1, r2, r3, r4, r5;
    public final int countElemets = 4;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void init() {
        storage.clear();
        //r1 = new Resume("uuid1", "FullName1");
        r1 = GenerateResume.genResume("uuid1", "Ivanov ivan");
        r2 = GenerateResume.genResume("uuid2", "FullName2");
        r3 = GenerateResume.genResume("uuid3", "FullName3");
        r4 = GenerateResume.genResume("uuid4", "FullName4");
        r5 = GenerateResume.genResume("uuid5", "FullName5");
        saveStorage();
    }

    @Test
    public void get() throws Exception {
        assertTrue(r1.equals(storage.get("uuid1")));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void save() {
        storage.save(r5);
        assertTrue(storage.size() == countElemets + 1);

    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(r1);
    }

    @Test
    public void update() throws Exception {
        Resume resumeBefore = r1;
        storage.update(r1);
        assertTrue(resumeBefore.equals(storage.get("uuid1")));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume("dummy", "dummy name"));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(r1.getUuid());
        assertTrue(countElemets - 1 == storage.size());
        storage.get(r1.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getAllSorted() {
        List<Resume> resultArr = storage.getAllSorted();
        List<Resume> expectedArr = Arrays.asList(r1, r2, r3, r4);
        assertEquals(expectedArr, resultArr);
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