package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MapStorage extends AbstractStorage {
    HashMap <String, Resume>storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllList() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Object object) {
        return object != null;
    }

    @Override
    protected void doUpdate(Object key, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void insert(Object key, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object object) {
        storage.remove(((Resume)object).getUuid());
    }

    @Override
    protected Object getSearchKey(String key) {
        return storage.get(key);
    }

    @Override
    protected Resume doGet(Object object) {
        return (Resume)object;
    }

}
