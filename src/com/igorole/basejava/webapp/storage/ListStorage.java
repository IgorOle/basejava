package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    ArrayList<Resume> storage = new ArrayList<Resume>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Object index) {
        try {
            storage.get((int)index);
        }
        catch (IndexOutOfBoundsException ex) {
            return false;
        }
        return true;
    }

    @Override
    protected void doUpdate(Object pos, Resume r) {
        storage.set((int) pos, r);
    }

    @Override
    protected void insert(int pos, Resume r) {
        storage.add(r);
    }

    @Override
    protected void doDelete(Object pos) {
        storage.remove((int)pos);
    }

    @Override
    protected int getIndex(String uuid) {
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
