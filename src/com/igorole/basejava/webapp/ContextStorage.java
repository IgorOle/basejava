package com.igorole.basejava.webapp;

import com.igorole.basejava.webapp.model.Resume;
import com.igorole.basejava.webapp.storage.AbstractStorage;

public class ContextStorage {
    private AbstractStorage storage;

    public void setStorage(AbstractStorage storage) {
        this.storage = storage;
    }

    public void save(Resume r) {
        storage.save(r);
    }
}
