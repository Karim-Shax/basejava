package com.urise.webapp.storage;

import com.urise.webapp.ResumeTestData;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.*;

import java.util.List;

public abstract class AbstractStorageTest {

    protected final Storage storage;
    protected static final File STORAGEDIR = new File("D:/base/basejava/storage");

    protected static final Resume RESUME_1;
    protected static final Resume RESUME_2;
    protected static final Resume RESUME_3;
    protected static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume("uuid1", "Aleksei");
        RESUME_2 = new Resume("uuid2", "Nikolai");
        RESUME_3 = ResumeTestData.defaultResume("uuid3", "Vasili");
        RESUME_4 = new Resume("uuid4", "Vasili");
    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clearTest() {
        storage.clear();
        assertEquals(0, storage.size());
        assertEquals(0, storage.getAllSortedList().size());
    }

    @Test()
    public void updateTest() {
        storage.update(RESUME_1);
        assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateExceptionTest() {
        storage.update(RESUME_4);
    }

    @Test
    public void sizeTest() {
        assertEquals(3, storage.size());
    }

    @Test()
    public void getTest() {
        assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getUuidExceptionTest() {
        storage.get(RESUME_4.getUuid());
    }

    @Test()
    public void getAllTest() {
        List<Resume> resumes = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        assertEquals(resumes, storage.getAllSortedList());
    }

    @Test()
    public void saveTest() {
        storage.save(RESUME_4);
        assertEquals(RESUME_4, storage.get(RESUME_4.getUuid()));
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExceptionTest() {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteTest() {
        storage.delete(RESUME_1.getUuid());
        assertEquals(2, storage.size());
        storage.get(RESUME_1.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteExceptionTest() {
        storage.delete(RESUME_4.getUuid());
    }
}
