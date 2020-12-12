package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = checkId(resume.getUuid());
        if (index == -1 && size < storage.length - 1) {
            storage[size++] = resume;
        } else if (index != -1) {
            System.out.println("Error " + resume.getUuid() + " is already on the array");
        } else {
            System.out.println("Storage overflowed");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = checkId(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Error " + uuid + " not found");
        }
    }

    @Override
    protected int checkId(String uuid) {
        int index = -1;
        if (size == 0 || uuid == null) {
            return index;
        }
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                index = i;
            }
        }
        return index;
    }


}