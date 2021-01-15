package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;


public abstract class AbstractStorage implements Storage {

    public Object getKeyIfResumeExist(String uuid) {
        Object key = getKey(uuid);
        if (checkKey(key))
            return key;
        else
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
        Object key = getKey(resume.getUuid());
        if (!checkKey(key))
            subSave(key, resume);
        else
            throw new ExistStorageException(resume.getUuid());
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


    protected abstract boolean checkKey(Object key);

    protected abstract Object getKey(String uuid);

    protected abstract void subUpdate(Object key, Resume resume);

    protected abstract Resume subGet(Object key);

    protected abstract void subSave(Object key, Resume resume);

    protected abstract void subDelete(Object key);


}
