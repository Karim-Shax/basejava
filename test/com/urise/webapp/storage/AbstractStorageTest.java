package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {

    protected final AbstractStorage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    public AbstractStorageTest(AbstractStorage storage) {
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
    public void clearTest() {
        storage.clear();
        assertEquals(0, storage.size());
        assertEquals(0, storage.getAll().length);
    }

    @Test()
    public void updateTest() {
        Resume r = new Resume(UUID_1);
        storage.update(r);
        assertEquals(r, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateExceptionTest() {
        storage.update(new Resume(UUID_4));
    }

    @Test
    public void sizeTest() {
        assertEquals(3, storage.size());
    }

    @Test()
    public void getTest() {
        Resume r = new Resume(UUID_1);
        assertEquals(r, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getUuidExceptionTest() {
        storage.get(UUID_4);
    }

    @Test()
    public void getAllTest() {
        Resume[] resumes = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        assertEquals(resumes.length, storage.size());
        int size = 0;
        for (Resume resume : resumes) {
            if (storage.get(resume.getUuid()).equals(resume)) {
                size++;
            }
        }
        assertEquals(size,resumes.length);
    }

    @Test()
    public void saveTest() {
        Resume resume = new Resume(UUID_4);
        storage.save(resume);
        assertEquals(resume, storage.get(UUID_4));
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExceptionTest() {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteTest() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteExceptionTest() {
        storage.delete(UUID_4);
    }


}
