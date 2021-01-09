package com.urise.webapp.storage;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapStorageTest extends AbstractStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }

    @Test()
    public void getAllTest() {
       assertEquals(super.storage.size(),super.storage.getAll().length);
    }
}