package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    protected int getIndex(String uuid) {
        for(int i = 0; i < size; i++) {
            if(storage[i] != null && storage[i].getUuid().equals(uuid))  {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insert(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void remove(int pos) {
        if(size > 1) {
            // переносим крайний элемент на освободившее место (избавление от дырок)
            storage[pos] = storage[size -1];
        }

    }

}
