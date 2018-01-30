package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    public static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected void doUpdate(Object pos, Resume r) {
        storage[(int) pos] = r;
    }

    @Override
    protected Resume doGet(Object pos) {
        return storage[(int) pos];
    }

    protected boolean isExist(Object searchKey) {
        return (int)searchKey >= 0;
    }

    protected void doDelete(Object pos) {
        int posInteger = (int)pos;
        storage[posInteger] = null;
        remove(posInteger);
        size--;
    }

    protected abstract void remove(int pos);
}