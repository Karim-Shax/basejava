package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;


public abstract class AbstractStorage implements Storage {

    public int containResume(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return index;
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void update(Resume resume) {
        int index = containResume(resume.getUuid());
        subUpdate(index, resume);
        System.out.println(resume.getUuid() + " updated");
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
        int index = containResume(uuid);
        return subGet(index, uuid);
    }

    @Override
    public void delete(String uuid) {
        int index = containResume(uuid);
        subDelete(index, uuid);
    }


    protected abstract int getIndex(String uuid);

    protected abstract void subUpdate(int index, Resume resume);

    protected abstract Resume subGet(int index, String uuid);

    protected abstract void subSave(int index, Resume resume);

    protected abstract void subDelete(int index, String uuid);

}
