package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.exception.ExistStorageException;
import com.igorole.basejava.webapp.exception.NotExistStorageException;
import com.igorole.basejava.webapp.exception.StorageException;
import com.igorole.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    public static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public Resume get(String uuid) {
        int pos = getIndex(uuid);
        if(pos < 0 ) {
            throw new NotExistStorageException(uuid);
        }
        return storage[pos];
    }

    public void save(Resume r) {
        int pos;
		if(size == STORAGE_LIMIT) {
			throw new StorageException("ERROR: storage is full", r.getUuid());
		}
		else {
            pos = getIndex(r.getUuid());
		    if( pos < 0) {
                insert(r, pos);
                size++;
            }
            else {
                throw new ExistStorageException(r.getUuid());
            }
        }
	}

    public void update(Resume r) {
        int pos = getIndex(r.getUuid());
        if (pos < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage[pos] = r;
        }
    }

    public void delete(String uuid) {
        int pos = getIndex(uuid);
        if (pos < 0) {
            throw new NotExistStorageException(uuid);
        } else {
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

    protected abstract int getIndex(String uuid);
    protected abstract void insert(Resume r, int index);
    protected abstract void remove(int pos);
}