package com.urise.webapp.storage.strategyclass;

import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IOStrategy {
    void writeObj(Resume r, OutputStream os) throws IOException;
    Resume readObj(InputStream is) throws IOException;
}
