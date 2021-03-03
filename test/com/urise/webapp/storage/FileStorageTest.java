package com.urise.webapp.storage;

import com.urise.webapp.storage.serialize.ObjectStreamImpl;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(STORAGEDIR, new ObjectStreamImpl()));
    }
}