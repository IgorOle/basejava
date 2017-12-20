package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.util.Arrays;


public class SortedArrayStorage extends AbstractArrayStorage{

    private void correctArrya(int from) {
        Arrays.sort(storage, from, size);
    }


    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        int s = Arrays.binarySearch(storage, 0, size, searchKey);
        return s;
    }

    @Override
    public void save(Resume r) {
        if(size == STORAGE_LIMIT) {
            System.out.println("ERROR: storage is full");
            return;
        }
        if( getIndex(r.getUuid()) < 0 ) {
            storage[size++] = r;
            correctArrya(0);
        }
        else {
            System.out.println("ERROR: resume exist");
        }
    }

    @Override
    public void delete(String uuid) {
        int pos = getIndex(uuid);

        if(pos >= 0) {
            // переносим крайний элемент на освободившее место (избавление от дырок)
            if(size > 1) {
                storage[pos] = storage[size -1];
            }
            storage[size -1] = null;
            size--;
            correctArrya(pos);
        }
    }

}