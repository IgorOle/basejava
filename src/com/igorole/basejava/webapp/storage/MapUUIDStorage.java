package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MapUUIDStorage extends AbstractStorage<String> {
    HashMap<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(String index) {
        return storage.containsKey(index);
    }

    @Override
    protected void doUpdate(String pos, Resume r) {
        storage.put(pos, r);
    }

    @Override
    protected void insert(String pos, Resume r) {
        storage.put(pos, r);
    }

    @Override
    protected void doDelete(String pos) {
        storage.remove(pos);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected Resume doGet(String pos) {
        return storage.get(pos);
    }

    @Override
    protected List<Resume> getAllList() {
        return new ArrayList<>(storage.values());
    }
}
