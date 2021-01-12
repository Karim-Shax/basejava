package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;


public abstract class AbstractStorage implements Storage {

    public Object getKeyIfResumeExist(String uuid) {
        int key = getKey(uuid);
        if (key >= 0) {
            if (key == 150)
                return uuid;
            else
                return key;
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void update(Resume resume) {
        Object key = getKeyIfResumeExist(resume.getUuid());
        subUpdate(key, resume);
        System.out.println(resume.getUuid() + " updated");
    }

    @Override
    public void save(Resume resume) {
        int key = getKey(resume.getUuid());
        if (key < 0) {
            if (key == -150)
                subSave(resume.getUuid(), resume);
            else
                subSave(key, resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        Object key = getKeyIfResumeExist(uuid);
        return subGet(key);
    }

    @Override
    public void delete(String uuid) {
        Object key = getKeyIfResumeExist(uuid);
        subDelete(key);
    }


    protected abstract int getKey(String uuid);

    protected abstract void subUpdate(Object key, Resume resume);

    protected abstract Resume subGet(Object key);

    protected abstract void subSave(Object key, Resume resume);

    protected abstract void subDelete(Object key);

}
