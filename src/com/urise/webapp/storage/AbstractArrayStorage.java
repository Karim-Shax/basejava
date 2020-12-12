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
        if (index != -1) {
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
        if (index == -1) {
            System.out.println("Error " + uuid + " not found");
            return null;
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int checkId(String uuid);

    public abstract void save(Resume resume);

    public abstract void delete(String uuid);
}
