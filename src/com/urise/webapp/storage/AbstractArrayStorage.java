package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = checkId(resume.getUuid());

        if (index >= 0) {
            storage[index] = resume;
            System.out.println(resume.getUuid() + " updated");
        } else {
            System.out.println("Error " + resume.getUuid() + " not found");
        }
    }

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = checkId(uuid);

        if (index < 0) {
            System.out.println("Error " + uuid + " not found");
            return null;
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }


    public void save(Resume resume) {
        int index = checkId(resume.getUuid());

        if (size == 0) {
            storage[0] = resume;
            size++;
        } else if (index < 0) {
            if (size < storage.length - 1) {
                sortedAndSave(resume, index);
                size++;
            } else {
                System.out.println("Storage overflowed");
            }
        } else {
            System.out.println("Error " + resume.getUuid() + " is already on the array");
        }
    }

    public void delete(String uuid) {
        int index = checkId(uuid);

        if (size != 0 && index >= 0) {
            sortandDelete(index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Error " + uuid + " not found");
        }
    }

    protected abstract int checkId(String uuid);

    protected abstract void sortandDelete(int index);

    protected abstract void sortedAndSave(Resume resume, int index);
}
