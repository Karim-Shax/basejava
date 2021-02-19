package com.urise.webapp.storage.serialize;

import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ObjectJsonImpl implements IOStrategy {

    @Override
    public void writeObj(Resume r, OutputStream os) throws IOException {

    }

    @Override
    public Resume readObj(InputStream is) throws IOException {
        return null;
    }
}
