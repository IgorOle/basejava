package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.io.File;
import java.io.IOException;

public class FileStorage extends AbstractFileStorage {
    public FileStorage(File directory) {
        super(directory);
    }

    @Override
    protected void doWrite(Resume r, File file) throws IOException {

    }

    @Override
    protected Resume doRead(File file) throws IOException {
        return null;
    }
}
