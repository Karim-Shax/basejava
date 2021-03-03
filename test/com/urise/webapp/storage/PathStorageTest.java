package com.urise.webapp.storage;

import com.urise.webapp.storage.serialize.ObjectDataImpl;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(STORAGEDIR.getPath(), new ObjectDataImpl()));
    }
}