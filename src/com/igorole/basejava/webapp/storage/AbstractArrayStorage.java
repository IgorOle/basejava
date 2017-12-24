package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public Resume get(String uuid) {
        int pos = getIndexWithMsg(uuid);
        return pos >= 0 ? storage[pos] : null;
    }

    public void save(Resume r) {
        int pos = getIndex(r.getUuid());
        if(size == STORAGE_LIMIT) {
            System.out.println("ERROR: storage is full");
            return;
        }
        if( pos < 0) {
            insert(r, pos);
            size++;
        }
        else {
            System.out.println("ERROR: resume exist");
        }
    }

    public void update(Resume r) {
        int pos = getIndexWithMsg(r.getUuid());
        if( pos >= 0) {
            storage[pos] = r;
        }
    }

    public void delete(String uuid) {
        int pos = getIndexWithMsg(uuid);
        if(pos >= 0) {
            remove(pos);
            size--;
        }
    }

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndexWithMsg(String uuid) {
        int pos = getIndex(uuid);
        if(pos < 0) System.out.println("ERROR: resume " + uuid + " not found");
        return pos;
    }

    protected abstract int getIndex(String uuid);
    protected abstract void insert(Resume r, int index);
    protected abstract void remove(int pos);
}