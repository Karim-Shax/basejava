package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 100;
    protected Resume[] storage = new Resume[100];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void subUpdate(int index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Resume subGet(int index, String uuid) {
        return storage[index];
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public void subSave(int index, Resume resume) {
        if (size < storage.length) {
            insertSave(resume, index);
            size++;
        } else {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
    }

    @Override
    public void subDelete(int index, String uuid) {
        delete(index);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void delete(int index);

    protected abstract void insertSave(Resume resume, int index);
}
