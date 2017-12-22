package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.util.Arrays;


public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    protected void insert(Resume r, int pos) {
        int insertIdx = -pos - 1;
        System.arraycopy(storage, insertIdx, storage, insertIdx + 1, size - insertIdx);
        storage[insertIdx] = r;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        int s = Arrays.binarySearch(storage, 0, size, searchKey);
        return s;
    }

    @Override
    public void delete(String uuid) {
        int pos = getIndexWithMsg(uuid);
        if(pos >= 0) {
            size--;
            System.arraycopy(storage, pos+1, storage, pos, size);
        }
    }

}