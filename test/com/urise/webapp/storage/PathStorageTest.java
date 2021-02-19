package com.urise.webapp.storage;

import com.urise.webapp.storage.strategyclass.ObjectStreamStorage;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(STORAGEDIR.getPath(), new ObjectStreamStorage()));
    }
}