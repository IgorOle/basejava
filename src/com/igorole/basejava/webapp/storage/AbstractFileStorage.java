package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.exception.StorageException;
import com.igorole.basejava.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory cant be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " permission error");
        }
        this.directory = directory;
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doUpdate(File file, Resume r) {
        try {
            doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Write error. File name is ", r.getUuid());
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("Delete error. File is ", file.getName());
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    public Resume doGet(File file) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Read error. File name is ", file.getName());
        }
    }

    @Override
    public List<Resume> getAllList() {
        File [] files = directory.listFiles();
        if (files == null) throw new StorageException("Storage is empty.", "");
        List<Resume> list = new ArrayList<>();
        for (File file : files) {
            list.add(doGet(file));
        }
        return list;
    }

    @Override
    public void clear() {
        File [] files = directory.listFiles();
        if (files == null) throw new StorageException("Storage is empty.", "");
        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    public int size() {
        File [] files = directory.listFiles();
        if (files == null) throw new StorageException("Storage is empty.", "");
        return files.length;
    }

    @Override
    public void insert(File file, Resume r) {
        try {
            doWrite(r, new FileOutputStream(file));
        } catch (IOException e) {
            throw new StorageException("Write error. File name is ", r.getUuid());
        }
    }
    protected abstract void doWrite(Resume r, OutputStream file) throws IOException;
    protected abstract Resume doRead(InputStream file) throws IOException;
}
