package com.igorole.basejava.webapp.storage;

import java.io.File;

public class ObjectStreamStorage extends AbstractFileStorage {
    public ObjectStreamStorage(File directory) {
        super(directory, new FileStream());
    }

}
