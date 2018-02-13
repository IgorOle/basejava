package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MapUUIDStorage extends AbstractStorage {
    HashMap <String, Resume>storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Object index) {
        return storage.containsKey(index);
    }

    @Override
    protected void doUpdate(Object pos, Resume r) {
        storage.put((String)pos, r);
    }

    @Override
    protected void insert(Object pos, Resume r) {
        storage.put((String)pos, r);
    }

    @Override
    protected void doDelete(Object pos) {
        storage.remove(pos);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected Resume doGet(Object pos) {
        return storage.get(pos);
    }

    @Override
    protected List<Resume> getAllList() {
        return new ArrayList<>(storage.values());
    }
}
