package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.exception.StorageException;
import com.igorole.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    protected void insert(Object pos, Resume r) {
        if(size == AbstractArrayStorage.STORAGE_LIMIT) throw new StorageException("Array is full", null);
        int insertIdx = -((int)pos) - 1;
        System.arraycopy(storage, insertIdx, storage, insertIdx + 1, size - insertIdx);
        storage[insertIdx] = r;
        size++;
    }

    @Override
    protected Object getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void remove(int pos) {
        System.arraycopy(storage, pos+1, storage, pos, size-1);
    }
}