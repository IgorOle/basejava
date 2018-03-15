package com.igorole.basejava.webapp.storage;

public class ObjectStreamPathStorage extends AbstractPathStorage {
    public ObjectStreamPathStorage(String directory) {
        super(directory, new FileStream());
    }

}
