package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapStorageByFullNameTest extends AbstractStorageTest {

    public MapStorageByFullNameTest() {
        super(new MapStorageByFullName());
    }

    @Override
    public void getTest() {
        Resume r = new Resume("uuid1", "Aleksei");
        assertEquals(r, storage.get("Aleksei"));
    }

    @Override
    public void saveTest() {
        Resume resume = new Resume("uuid4", "Georgi");
        storage.save(resume);
        assertEquals(resume, storage.get("Georgi"));
        assertEquals(4, storage.size());
    }

    @Override
    @Test(expected = NotExistStorageException.class)
    public void deleteTest() {
        storage.delete("Aleksei");
        assertEquals(2, storage.size());
        storage.get("Aleksei");
    }
    @Test()
    public void updateTest() {
        Resume r = new Resume("uuid1", "Aleksei");
        storage.update(r);
        assertEquals(r, storage.get("Aleksei"));
    }
}