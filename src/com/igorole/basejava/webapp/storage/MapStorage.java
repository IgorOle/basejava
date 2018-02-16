package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MapStorage extends AbstractStorage<Resume> {
    HashMap<String, Resume> storage = new HashMap<>();

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
    protected boolean isExist(Resume Resume) {
        return Resume != null;
    }

    @Override
    protected void doUpdate(Resume key, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void insert(Resume key, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Resume Resume) {
        storage.remove((Resume).getUuid());
    }

    @Override
    protected Resume getSearchKey(String key) {
        return storage.get(key);
    }

    @Override
    protected Resume doGet(Resume Resume) {
        return Resume;
    }

}
