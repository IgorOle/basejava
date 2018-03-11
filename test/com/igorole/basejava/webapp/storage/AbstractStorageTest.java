package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.GenerateResume;
import com.igorole.basejava.webapp.exception.ExistStorageException;
import com.igorole.basejava.webapp.exception.NotExistStorageException;
import com.igorole.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AbstractStorageTest {

    Storage storage;
    Resume r1, r2, r3, r4;
    private final int countElemets = 4;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void init() {
        storage.clear();
//        r1 = new Resume("uuid1", "ResumeA");
//        r2 = new Resume("uuid2", "ResumeB");
//        r3 = new Resume("uuid32", "ResumeC");
//        r4 = new Resume("uuid31", "ResumeC");
        try {
            r1 = GenerateResume.genResume("uuid1");
            r2 = GenerateResume.genResume("uuid2");
            r3 = GenerateResume.genResume("uuid32");
            r4 = GenerateResume.genResume("uuid31");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        saveStorage();
    }

    @Test
    public void get() throws Exception {
        assertSame(r1, storage.get("uuid1"));
    }

    @Test(expected = ExistStorageException.class)
    public void save() {
        storage.save(r1);
    }

    @Test
    public void update() throws Exception {
        Resume resumeBefore = r1;
        storage.update(r1);
        assertTrue(resumeBefore == storage.get("uuid1"));
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
        System.out.println(resultArr);
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