package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.io.*;

public class FileStorage extends AbstractFileStorage {
    public FileStorage(File directory) {
        super(directory);
    }

    @Override
    protected void doWrite(Resume r, OutputStream file) throws IOException {

    }

    @Override
    protected Resume doRead(InputStream file) throws IOException {
        return null;
    }
}
