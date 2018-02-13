package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.exception.StorageException;
import com.igorole.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverFlow() {
        int countResume = storage.size();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT - countResume; i++) {
                storage.save(new Resume("Resume"+i));
            }
        } catch (StorageException ex) {
            Assert.fail();
        }
        storage.save(new Resume("ResumeX"));
    }
}