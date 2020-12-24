package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String uuid1 = "uuid1";
    private static final String uuid2 = "uuid2";
    private static final String uuid3 = "uuid3";
    private static final String uuid4 = "uuid4";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(uuid1));
        storage.save(new Resume(uuid2));
        storage.save(new Resume(uuid3));
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
        assertEquals(0, storage.getAll().length);
    }

    @Test()
    public void update() {
        Resume r = new Resume(uuid1);
        storage.update(r);
        assertEquals(r, storage.get(uuid1));
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test()
    public void get() {
        Resume r = storage.get(uuid1);
        assertEquals(uuid1, r.getUuid());
    }

    @Test()
    public void getAll() {
        Resume[] resumes = {
                new Resume("uuid1"),
                new Resume("uuid2"),
                new Resume("uuid3"),
        };
        assertArrayEquals(resumes, storage.getAll());
    }

    @Test()
    public void save() {
        storage.clear();
        Resume resume = new Resume(uuid1);
        storage.save(resume);
        assertEquals(resume, storage.get(uuid1));
        assertEquals(1, storage.size());
    }

    @Test()
    public void delete() {
        storage.delete(uuid1);
        assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws NotExistStorageException {
        storage.get("imy");
    }

    @Test
    public void exceptionTest() {
        try {
            storage.get(uuid4);
            fail("uuid4 not exist");
        } catch (Exception ignored) {
            try {
                storage.update(new Resume(uuid4));
                fail("resume not exist");
            } catch (Exception ignored1) {
                try {
                    storage.delete(uuid4);
                    fail("resume not exist");
                } catch (Exception ignored2) {
                    try {
                        storage.save(new Resume(uuid1));
                        fail("resume already exist");
                    } catch (Exception ignored3) {
                        System.out.println("test owned");
                    }
                }
            }
        }

    }
}