package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.exception.ExistStorageException;
import com.igorole.basejava.webapp.exception.NotExistStorageException;
import com.igorole.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage{

    public void save(Resume r) {
        insert(getNotExistedIndex(r.getUuid()), r);
    }

    public void update(Resume r) {
        doUpdate(getExistedIndex(r.getUuid()), r);
    }

    public Resume get(String uuid){
        return doGet(getExistedIndex(uuid));
    }

    public void delete(String uuid){
        doDelete(getExistedIndex(uuid));
    }

    private Object getExistedIndex(String uuid) {
        Object index = getIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    private Object getNotExistedIndex(String uuid) {
        Object index = getIndex(uuid);
        if (isExist(index)) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    protected abstract boolean isExist(Object index);
    protected abstract void doUpdate(Object pos, Resume r);
    protected abstract void insert(Object pos, Resume r);
    protected abstract void doDelete(Object pos);
    protected abstract Object getIndex(String uuid);
    protected abstract Resume doGet(Object pos);

}
