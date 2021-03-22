package com.urise.webapp.storage;

import com.urise.webapp.Config;

public class StorageDBTest extends AbstractStorageTest {

    public StorageDBTest() {
        super(
                new StorageDB(
                        Config.getConfig().getUrl(),
                        Config.getConfig().getName(),
                        Config.getConfig().getPassword()
                )
        );
    }
}