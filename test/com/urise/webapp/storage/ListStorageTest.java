package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ListStorageTest extends AbstractStorageTest {
    /*

        private final ListStorage storage;

        private static final Resume RESUME1 = new Resume("UUID1");
        private static final Resume RESUME2 = new Resume("UUID2");
        private static final Resume RESUME3 = new Resume("UUID3");
        private static final Resume RESUME4 = new Resume("UUID4");
    */
    public ListStorageTest() {
        super(new ListStorage());
    }
/*
    @Before
    public void setUp() throws Exception {
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }

    @Test()
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
        assertEquals(0, storage.getAll().length);
    }

    @Test()
    public void update() {
        storage.update(RESUME2);
    }

    @Test()
    public void save() {
        storage.save(RESUME4);
        assertEquals(4, storage.size());
    }

    @Test()
    public void get() {
        assertEquals(RESUME1, storage.get(RESUME1.getUuid()));
    }

    @Test()
    public void delete() {
        storage.delete(RESUME3.getUuid());
        assertEquals(2, storage.size());
    }

    @Test
    public void getAll() {

    }

    @Test
    public void size() {

    }*/
}