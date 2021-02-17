package com.urise.webapp.storage;


import com.urise.webapp.storage.strategyclass.ObjectStreamStorage;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(STORAGEDIR, new ObjectStreamStorage()));
    }
}