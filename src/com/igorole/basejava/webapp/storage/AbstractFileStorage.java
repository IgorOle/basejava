package com.igorole.basejava.webapp.storage;

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
        insert(file, r);
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
        Resume r = null;
        try (FileInputStream fin = new FileInputStream(file.getAbsolutePath());
             ObjectInputStream ois = new ObjectInputStream(fin)) {
            r = (Resume) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public List<Resume> getAllList() {
        List <Resume> list = new ArrayList<Resume>() ;
        for (File file: directory.listFiles()) {
            list.add(doGet(file));
        }
        return list;
    }

    @Override
    public void clear() {
        for (File file : directory.listFiles()) {
            if (file.isFile()) file.delete();
        }
    }

    @Override
    public int size() {
        return directory.listFiles().length;
    }

    @Override
    public void insert(File file, Resume r) {
        try (FileOutputStream fout = new FileOutputStream(file.getAbsolutePath());
             ObjectOutputStream oos = new ObjectOutputStream(fout)) {
            oos.writeObject(r);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
