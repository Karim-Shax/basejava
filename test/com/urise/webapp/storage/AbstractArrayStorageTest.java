package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
        assertEquals(0, storage.getAll().length);
    }

    @Test()
    public void update() {
        Resume r = new Resume(UUID_1);
        storage.update(r);
        assertEquals(r, storage.get(UUID_1));
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test()
    public void get() {
        Resume r = storage.get(UUID_1);
        assertEquals(UUID_1, r.getUuid());
    }

    @Test()
    public void getAll() {
        Resume[] resumes = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        assertArrayEquals(resumes, storage.getAll());
    }

    @Test()
    public void save() {
        storage.clear();
        Resume resume = new Resume(UUID_1);
        storage.save(resume);
        assertEquals(resume, storage.get(UUID_1));
        assertEquals(1, storage.size());
    }

    @Test()
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws NotExistStorageException {
        storage.get("imy");
    }

    @Test
    public void exceptionTest() {
        try {

        } catch (Exception ignored) {
            try {

            } catch (Exception ignored1) {
                try {

                } catch (Exception ignored2) {

                }
            }
        }
    }

    @Test
    public void getExceptionTest() {
        try {
            storage.get(UUID_4);
            fail("uuid4 not exist (get)");
        } catch (Exception e) {

        }
    }

    @Test
    public void updateExceptionTest() {
        try {
            storage.update(new Resume(UUID_4));
            fail("resume not exist (update)");
        } catch (Exception e) {

        }
    }

    @Test
    public void deleteExceptionTest() {
        try {
            storage.delete(UUID_4);
            fail("resume not exist (delete)");
        } catch (Exception e) {

        }
    }

    @Test
    public void saveExceptionTest() {
        try {
            storage.save(new Resume(UUID_1));
            fail("resume already exist (save)");
        } catch (Exception ignored3) {
            System.out.println("test owned");
        }
    }

}