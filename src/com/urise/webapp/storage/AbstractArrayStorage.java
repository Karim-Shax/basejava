package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 100;
    protected Resume[] storage = new Resume[100];
    protected int size = 0;

    @Override
    public void subClear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void subUpdate(Resume resume) {
        int index = checkId(resume.getUuid());

        if (index >= 0) {
            storage[index] = resume;
            System.out.println(resume.getUuid() + " updated");
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public int subSize() {
        return size;
    }

    @Override
    public Resume subGet(String uuid) {
        int index = checkId(uuid);

        if (index >= 0) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public Resume[] subGetAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public void subSave(Resume resume) {
        int index = checkId(resume.getUuid());
        if (index < 0) {
            if (size < storage.length) {
                insertSave(resume, index);
                size++;
            } else {
                throw new StorageException("Storage overflow", resume.getUuid());
            }
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void subDelete(String uuid) {
        int index = checkId(uuid);

        if (size != 0 && index >= 0) {
            delete(index);
            storage[size - 1] = null;
            size--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract int checkId(String uuid);

    protected abstract void delete(int index);

    protected abstract void insertSave(Resume resume, int index);
}
