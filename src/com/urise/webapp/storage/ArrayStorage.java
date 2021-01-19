package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertSave(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    public void delete(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected Object getKey(Resume resume) {
        int index = -1;
        if (size == 0 || resume == null) {
            return index;
        }
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(resume)) {
                index = i;
            }
        }
        return index;
    }

}