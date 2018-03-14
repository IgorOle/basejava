package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.io.*;

public class FileStorage extends AbstractFileStorage {
    public FileStorage(File directory) {
        super(directory);
    }

    @Override
    protected void doWrite(Resume r, OutputStream file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(file)) {
            oos.writeObject(r);
        }
    }

    @Override
    protected Resume doRead(InputStream file) throws IOException {
        Resume r = null;
        try (ObjectInputStream ois = new ObjectInputStream(file)) {
            r = (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return r;
    }
}
