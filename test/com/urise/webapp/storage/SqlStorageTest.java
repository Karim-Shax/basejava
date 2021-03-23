package com.urise.webapp.storage;

import com.urise.webapp.sql.Config;

public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(Config.getConfig().sqlStorage());
    }
}