package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Iterator;

public class ListStorage extends AbstractStorage {
    ArrayList<Resume> storage = new ArrayList<Resume>();
    @Override
    protected void doUpdate(int pos, Resume r) {
        storage.set(pos, r);
    }

    @Override
    protected void insert(int pos, Resume r) {
        storage.add(r);
    }

    @Override
    protected void doDelete(int pos) {
        storage.remove(pos);
    }


    @Override
    protected int getIndex(String uuid) {
        for(int i = 0; i < storage.size(); i++) {
            if(storage.get(i).getUuid().equals(uuid))
                return i;
        }
        return -1;
    }

    @Override
    protected Resume doGet(int pos) {
        return storage.get(pos);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray();
    }

    @Override
    public int size() {
        return 0;
    }
}
