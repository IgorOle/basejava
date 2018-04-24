package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.exception.ExistStorageException;
import com.igorole.basejava.webapp.exception.NotExistStorageException;
import com.igorole.basejava.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private final static Logger LOG = Logger.getLogger(AbstractArrayStorage.class.getName());

    public void save(Resume r) {
        insert(getNotExistedIndex(r.getUuid()), r);
    }

    public void update(Resume r) {
        doUpdate(getExistedIndex(r.getUuid()), r);
    }

    public Resume get(String uuid) {
        return doGet(getExistedIndex(uuid));
    }

    public void delete(String uuid) {
        doDelete(getExistedIndex(uuid));
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = getAllList();
        Collections.sort(list);
        return list;
    }

    protected SK getExistedIndex(String uuid) {
        SK index = getSearchKey(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    protected SK getNotExistedIndex(String uuid) {
        SK index = getSearchKey(uuid);
        if (isExist(index)) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    protected abstract boolean isExist(SK index);

    protected abstract void doUpdate(SK pos, Resume r);

    protected abstract void insert(SK pos, Resume r);

    protected abstract void doDelete(SK pos);

    protected abstract SK getSearchKey(String uuid);

    protected abstract Resume doGet(SK pos);

    protected abstract List<Resume> getAllList();
}
