package com.igorole.basejava.webapp.storage;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(STORAGE_DIR.toString()));
    }
}