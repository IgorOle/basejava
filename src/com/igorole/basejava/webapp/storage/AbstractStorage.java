package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.exception.ExistStorageException;
import com.igorole.basejava.webapp.exception.NotExistStorageException;
import com.igorole.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage{

    public void save(Resume r) {
        int pos = getNotExistedIndex(r.getUuid());
        insert(pos, r);
    }

    public void update(Resume r) {
        Object pos = getExistedIndex(r.getUuid());
        doUpdate(pos, r);
    }

    public Resume get(String uuid){
        Object pos = getExistedIndex(uuid);
        return doGet(pos);
    }

    public void delete(String uuid){
        Object pos = getExistedIndex(uuid);
        doDelete(pos);
    }

    private Object getExistedIndex(String uuid) {
        Object index = getIndex(uuid);
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

    protected abstract boolean isExist(Object index);
    protected abstract void doUpdate(Object pos, Resume r);
    protected abstract void insert(int pos, Resume r);
    protected abstract void doDelete(Object pos);
    protected abstract int getIndex(String uuid);
    protected abstract Resume doGet(Object pos);

}
