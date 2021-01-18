package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.model.Resume;

public class MapStorageByFullName extends MapStorage {

    @Override
    public void update(Resume resume) {
        Object key = getKeyIfResumeExist(resume.getFullName());
        subUpdate(key, resume);
        System.out.println(resume.getFullName() + " updated");
    }

    @Override
    public void save(Resume resume) {
        Object key = getKey(resume.getFullName());
        if (!checkKey(key))
            subSave(key, resume);
        else
            throw new ExistStorageException(resume.getFullName());
    }

    @Override
    protected void subSave(Object uuid, Resume resume) {
        resumeMap.putIfAbsent(resume.getFullName(), resume);
    }
}
