package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;

public class ObjectStreamStorage implements IOStrategy {

    @Override
    public void writeObj(Resume r, OutputStream os) throws IOException {
        try (ObjectOutputStream obs = new ObjectOutputStream(os)) {
            obs.writeObject(r);
        }
    }

    @Override
    public Resume readObj(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException(e.getMessage(), null);
        }
    }
}
