package com.igorole.basejava.webapp.storage;

import java.io.File;

public class FileStorage extends AbstractFileStorage {
    public FileStorage(File directory) {
        super(directory, new FileStream());
    }

}
