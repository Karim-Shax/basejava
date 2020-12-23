package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {

    private final Storage storage;

    private static final String u1 = "uuid1";
    private static final String u2 = "uuid2";
    private static final String u3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws StorageException {
        storage.clear();
        storage.save(new Resume(u1));
        storage.save(new Resume(u2));
        storage.save(new Resume(u3));
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
        assertEquals(0, storage.getAll().length);
    }

    @Test()
    public void update() {
        Resume r = new Resume(u1);
        storage.update(r);
        assertEquals(r, storage.get(u1));
        try {
            storage.update(new Resume("uuid4"));
            fail("resume not exist");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test()
    public void get() {
        Resume r = storage.get(u1);
        assertEquals(u1, r.getUuid());
        try {
            storage.get("uuid4");
            fail("uuid4 not exist");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test()
    public void getAll() {
        Resume[] resumes = storage.getAll();
        boolean result = true;
        if (resumes != null) {
            for (Resume r : resumes) {
                if (!r.equals(storage.get(r.getUuid()))) {
                    result = false;
                }
            }
        } else {
            result = false;
        }
        assertTrue(result);
    }

    @Test()
    public void save() {
        storage.clear();
        Resume[] resumes = {
                new Resume("uuid0"),
                new Resume("uuid1"),
                new Resume("uuid2"),
                new Resume("uuid3"),
                new Resume("uuid4"),
                new Resume("uuid5"),
                new Resume("uuid6"),
                new Resume("uuid7"),
                new Resume("uuid8"),
                new Resume("uuid9")
        };

        try {
            for (int i = 0; i < 10; i++) {
                storage.save(new Resume("uuid" + i));
                if (!resumes[i].equals(storage.get("uuid" + i))) {
                    fail("storage not saved resumes");
                }
            }
        } catch (Exception e) {
            fail("overflow happened earlier");
        }
        try {
            storage.save(new Resume("uuid11"));
            fail("storage overflowed");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test()
    public void delete() {
        storage.delete(u1);
        assertEquals(2, storage.size());
        try {
            storage.delete(u1);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws NotExistStorageException {
        storage.get("imy");
    }
}