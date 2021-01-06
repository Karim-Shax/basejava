package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;


public abstract class AbstractStorage implements Storage {

    @Override
    public void clear() {
        subClear();
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index >= 0) {
            subUpdate(index, resume);
            System.out.println(resume.getUuid() + " updated");
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            subSave(index, resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index >= 0) {
            return subGet(index);
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        int size = size();
        if (size != 0 && index >= 0) {
            subDelete(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public Resume[] getAll() {
        return subGetAll();
    }

    @Override
    public int size() {
        return subSize();
    }


    protected abstract int getIndex(String uuid);

    protected abstract void subClear();

    protected abstract void subUpdate(int index, Resume resume);

    protected abstract Resume subGet(int index);

    protected abstract void subSave(int index, Resume resume);

    protected abstract void subDelete(int index);

    protected abstract Resume[] subGetAll();

    protected abstract int subSize();

}
