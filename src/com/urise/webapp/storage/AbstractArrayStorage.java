package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 100;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void subUpdate(Integer index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Resume subGet(Integer index) {
        return storage[index];
    }

    @Override
    public List<Resume> getAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    public void subSave(Integer index, Resume resume) {
        if (size < storage.length) {
            insertSave(resume, index);
            size++;
        } else {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
    }

    @Override
    public void subDelete(Integer index) {
        delete(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean checkKey(Integer key) {
        return key >= 0;
    }

    protected abstract void delete(int index);

    protected abstract void insertSave(Resume resume, int index);
}
