package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    abstract protected void insert(Resume r, int index);

    protected int getIndex(String uuid) {
        for(int i = 0; i < size; i++) {
            if(storage[i] != null && storage[i].getUuid().equals(uuid))  {
                return i;
            }
        }
        return -1;
    }

    protected int getIndexWithMsg(String uuid) {
        int pos = getIndex(uuid);
        if(pos == -1) System.out.println("ERROR: resume " + uuid + " not found");
        return pos;
    }

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
            if(size > 1) {
                // переносим крайний элемент на освободившее место (избавление от дырок)
                storage[pos] = storage[size -1];
            }
            storage[size -1] = null;
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
}