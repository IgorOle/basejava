package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insert(Resume r, int index) {
        storage[size] = r;
    }
}
