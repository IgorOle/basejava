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
    protected void doUpdate(int pos, Resume r) {
        storage[pos] = r;
    }

    protected void doDelete(int pos) {
        storage[pos] = null;
        remove(pos);
        size--;
    }

    @Override
    protected Resume doGet(int pos) {
        return storage[pos];
    }

    protected abstract void remove(int pos);
}