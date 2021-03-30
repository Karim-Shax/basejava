package com.urise.webapp.storage;

import com.urise.webapp.ResumeTestData;
import com.urise.webapp.sql.Config;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.*;

import java.util.EnumMap;
import java.util.List;
import java.util.UUID;

public abstract class AbstractStorageTest {

    protected final Storage storage;
    protected static final File STORAGEDIR = Config.getConfig().getStorageDir();

    protected static final Resume RESUME_1;
    protected static final Resume RESUME_2;
    protected static final Resume RESUME_3;
    protected static final Resume RESUME_4;
    protected static final String UUID1 = UUID.randomUUID().toString();
    protected static final String UUID2 = UUID.randomUUID().toString();
    protected static final String UUID3 = UUID.randomUUID().toString();
    protected static final String UUID4 = UUID.randomUUID().toString();

    static {
        RESUME_1 = ResumeTestData.defaultResume(UUID1, "Aleksei");
        RESUME_2 = ResumeTestData.defaultResume(UUID2, "Nikolai");
        RESUME_3 = ResumeTestData.defaultResume(UUID3, "Vladimir");
        RESUME_4 = ResumeTestData.defaultResume(UUID4, "Vasili");
    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        storage.save(RESUME_1);
    }

    @After
    public void close() {
        storage.clear();
    }

    @Test
    public void clearTest() {
        storage.clear();
        assertEquals(0, storage.size());
        assertEquals(0, storage.getAllSortedList().size());
    }

    @Test()
    public void updateTest() {
        Resume update = new Resume(UUID1, RESUME_4.getFullName());
        update.setContacts((EnumMap<ContactType, String>) RESUME_4.getContacts());
        storage.update(update);
        assertEquals(update, storage.get(UUID1));
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
