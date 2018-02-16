package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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

    @Override
    protected List<Resume> getAllList() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    protected void doUpdate(Integer pos, Resume r) {
        storage[pos] = r;
    }

    @Override
    protected Resume doGet(Integer pos) {
        return storage[pos];
    }

    protected boolean isExist(Integer searchKey) {
        return (searchKey) >= 0;
    }

    protected void doDelete(Integer pos) {
        int posInteger = pos;
        storage[posInteger] = null;
        remove(posInteger);
        size--;
    }

    protected abstract void remove(int pos);
}