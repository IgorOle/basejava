package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllList() {
        return new ArrayList<>(storage);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Object index) {
        return (int)index >= 0;
    }

    @Override
    protected void doUpdate(Object pos, Resume r) {
        storage.set((int) pos, r);
    }

    @Override
    protected void insert(Object pos, Resume r) {
        storage.add(r);
    }

    @Override
    protected void doDelete(Object pos) {
        storage.remove((int)pos);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid))
                return i;
        }
        return -1;
    }

    @Override
    protected Resume doGet(Object pos) {
        return storage.get((int) pos);
    }
}
