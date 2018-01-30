package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;


public class MapStorage extends AbstractStorage {
    @Override
    public void clear() {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    protected boolean isExist(Object index) {
        return false;
    }

    @Override
    protected void doUpdate(Object pos, Resume r) {    }

    @Override
    protected void insert(int pos, Resume r) {    }

    @Override
    protected void doDelete(Object pos) {    }

    @Override
    protected int getIndex(String uuid) {
        return 0;
    }

    @Override
    protected Resume doGet(Object pos) {
        return null;
    }

}
