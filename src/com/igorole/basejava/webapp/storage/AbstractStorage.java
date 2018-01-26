package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.exception.ExistStorageException;
import com.igorole.basejava.webapp.exception.NotExistStorageException;
import com.igorole.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage{

    public void update(Resume r) {
        int pos = getExistedIndex(r.getUuid());
        doUpdate(pos, r);
    }

    public void save(Resume r) {
        int pos = getNotExistedIndex(r.getUuid());
        insert(pos, r);
    }
    public void delete(String uuid){
        int pos = getExistedIndex(uuid);
        doDelete(pos);
    }

    public Resume get(String uuid){
        int pos = getExistedIndex(uuid);
        return doGet(pos);
    }

    private int getExistedIndex(String uuid) {
        int index = getIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    private int getNotExistedIndex(String uuid) {
        int index = getIndex(uuid);
        if (isExist(index)) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    private boolean isExist(int searchKey) {
        return (searchKey < 0) ? false : true;
    }
    protected abstract void doUpdate(int pos, Resume r);
    protected abstract void insert(int pos, Resume r);
    protected abstract void doDelete(int pos);
    protected abstract void remove(int pos);
    protected abstract int getIndex(String uuid);
    protected abstract Resume doGet(int pos);

}
