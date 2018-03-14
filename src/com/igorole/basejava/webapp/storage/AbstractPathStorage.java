package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.exception.StorageException;
import com.igorole.basejava.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected AbstractPathStorage(String dir) {
        Objects.requireNonNull(dir, "directory cant be null");
        this.directory = Paths.get(dir);
    }

    @Override
    protected boolean isExist(Path file) {
        return Files.exists(file, LinkOption.NOFOLLOW_LINKS);
    }

    @Override
    protected void doUpdate(Path file, Resume r) {
        insert(file, r);
    }

    @Override
    protected void doDelete(Path file) {
        try {
            Files.delete(file);
        } catch (IOException e) {
            throw new StorageException("Delete error. File is " + file.getFileName(), null);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return Paths.get(directory + "/" + uuid);
    }

    @Override
    public Resume doGet(Path file) {

        try {
            return doRead(Files.newInputStream(file));
        } catch (IOException e) {
            throw new StorageException("Read error. File name is " + file, null);
        }
    }

    @Override
    public List<Resume> getAllList() {
        List<Resume> list = new ArrayList<>();
        try {
            Files.list(directory).forEach(path -> list.add(doGet(path)));
        } catch (IOException e) {
            throw new StorageException("Read error from directory ", directory.toString());
        }
        return list;

    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Error clear." + e.getMessage(), null);
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Error szie." + e.getMessage(), null);
        }
    }

    @Override
    public void insert(Path file, Resume r) {
        try {
            doWrite(r, Files.newOutputStream(file));
        } catch (IOException e) {
            throw new StorageException("Error insert." + e.getMessage(), null);
        }
    }

    protected abstract void doWrite(Resume r, OutputStream file) throws IOException;

    protected abstract Resume doRead(InputStream file) throws IOException;
}
