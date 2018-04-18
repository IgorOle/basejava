package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.exception.StorageException;
import com.igorole.basejava.webapp.model.Resume;
import com.igorole.basejava.webapp.storage.serializer.StreamIO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;
    StreamIO stream;

    public PathStorage(String dir, StreamIO stream) {
        Objects.requireNonNull(dir, "directory cant be null");
        this.directory = Paths.get(dir);
        this.stream = stream;
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
            throw new StorageException("Delete error. File is " + file.getFileName(), null, e);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return Paths.get(directory + "/" + uuid);
    }

    @Override
    public Resume doGet(Path file) {
        try {
            return stream.doRead(Files.newInputStream(file));
        } catch (IOException e) {
            throw new StorageException("Read error. File name is " + file, null, e);
        }
    }

    @Override
    public List<Resume> getAllList() {
        List<Resume> list = new ArrayList<>();
        getFilesList().forEach(path -> list.add(doGet(path)));
        return list;
    }

    @Override
    public void clear() {
        getFilesList().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    @Override
    public void insert(Path file, Resume r) {
        try {
            stream.doWrite(r, Files.newOutputStream(file));
        } catch (IOException e) {
            throw new StorageException("Error insert." + e.getMessage(), null, e);
        }
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Error directory" + e.getMessage(), null, e);
        }
    }
}
