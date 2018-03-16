package com.igorole.basejava.webapp.storage;

import com.igorole.basejava.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamIO {
    void doWrite(Resume r, OutputStream file) throws IOException;

    Resume doRead(InputStream file) throws IOException;
}
