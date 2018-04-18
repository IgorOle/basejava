package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.exception.StorageException;
import com.igorole.basejava.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insert(Integer index, Resume r) {
        storage[size] = r;
        size++;
    }

    @Override
    protected void remove(int pos) {
        if (size > 1) {
            // переносим крайний элемент на освободившее место (избавление от дырок)
            storage[pos] = storage[size - 1];
        }
    }

    protected Integer getSearchKey(String uuid) {
        if (size == AbstractArrayStorage.STORAGE_LIMIT) throw new StorageException("Array is full", uuid, null);
        for (int i = 0; i < size; i++) {
            if (storage[i] != null && storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
