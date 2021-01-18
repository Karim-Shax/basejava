package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void subUpdate(Object index, Resume resume) {
        storage[(int) index] = resume;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Resume subGet(Object index) {
        return storage[(int) index];
    }

    @Override
    public List<Resume> getAllSortedList() {
        if (size == 0) {
            return new ArrayList<>();
        }
        ArrayList<Resume> list = new ArrayList<>(Arrays.asList(Arrays.copyOf(storage,size)));
        list.sort(new ResumeComparatorByFullName().thenComparing(new ResumeCompareByUUid()));
        return list;
    }

    @Override
    public void subSave(Object index, Resume resume) {
        if (size < storage.length) {
            insertSave(resume, (int) index);
            size++;
        } else {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
    }

    @Override
    public void subDelete(Object index) {
        delete((int) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean checkKey(Object key) {
        return (int) key >= 0;
    }

    protected abstract void delete(int index);

    protected abstract void insertSave(Resume resume, int index);
}
