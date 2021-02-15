package com.urise.webapp.storage;

import java.io.File;

public class AbstractFileStorageTest extends AbstractStorageTest {
    public AbstractFileStorageTest() {
        super(new AbstractFileStorage(new File("D:/base/basejava/storage"), new ObjectStreamStorage()));
    }
}