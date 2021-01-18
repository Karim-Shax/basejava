package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Test;


import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {


    public AbstractArrayStorageTest(AbstractArrayStorage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void storageOverflowTest() {
        try {
            for (int i = storage.size() + 1; i < AbstractArrayStorage.STORAGE_LIMIT + 1; i++) {
                storage.save(new Resume("uuid" + i, "uuid" + i));
            }
        } catch (Exception ignore) {
            fail("storage aren't overflowed");
        }
        storage.save(new Resume("uuid101", "uuid101"));
    }
}