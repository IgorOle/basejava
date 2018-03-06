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
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("Write error. File name is ", r.getUuid());
        }
    }

    @Override
    protected void doDelete(File file) {
        file.delete();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    public Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("Read error. File name is ", file.getName());
        }
//        Resume r = null;
//        try (FileInputStream fin = new FileInputStream(file.getAbsolutePath());
//             ObjectInputStream ois = new ObjectInputStream(fin)) {
//            r = (Resume) ois.readObject();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return r;
    }

    @Override
    public List<Resume> getAllList() {
        if (directory.listFiles() == null) return null;
        List<Resume> list = new ArrayList<Resume>();
        for (File file : directory.listFiles()) {
            list.add(doGet(file));
        }
        return list;
    }

    @Override
    public void clear() {
        if (directory.listFiles() == null) return;
        for (File file : directory.listFiles()) {
            if (file.isFile()) file.delete();
        }
    }

    @Override
    public int size() {
        if (directory.listFiles() == null) return 0;
        return directory.listFiles().length;
    }

//    @Override
//    public void insert(File file, Resume r) {
//        try (FileOutputStream fout = new FileOutputStream(file.getAbsolutePath());
//             ObjectOutputStream oos = new ObjectOutputStream(fout)) {
//            oos.writeObject(r);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    protected abstract void doWrite(Resume r, File file) throws IOException;
    protected abstract Resume doRead(File file) throws IOException;
}
