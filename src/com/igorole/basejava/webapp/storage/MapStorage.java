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
    protected void doUpdate(int pos, Resume r) {

    }

    @Override
    protected void insert(int pos, Resume r) {

    }

    @Override
    protected void doDelete(int pos) {

    }

    @Override
    protected Resume doGet(int pos) {
        return null;
    }

    @Override
    protected int getIndex(String uuid) {
        return 0;
    }

}
