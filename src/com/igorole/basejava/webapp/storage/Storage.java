package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.io.IOException;
import java.util.List;

public interface Storage {

    void clear();

    void update(Resume r);

    void save(Resume r);

    Resume get(String uuid) throws IOException;

    void delete(String uuid);

    List<Resume> getAllSorted();

    int size();
}
