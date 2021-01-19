package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public abstract class AbstractStorage implements Storage {

    public Object getKeyIfResumeExist(Resume resume) {
        Object key = getKey(resume);
        if (checkKey(key))
            return key;
        else
            throw new NotExistStorageException(resume.getFullName());
    }

    @Override
    public List<Resume> getAllSortedList() {
        ArrayList<Resume> list = new ArrayList<>(getAll());
        Comparator<Resume> comparator = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
        list.sort(comparator);
        return list;
    }

    @Override
    public void update(Resume resume) {
        Object key = getKeyIfResumeExist(resume);
        subUpdate(key, resume);
        System.out.println(resume.getUuid() + " updated");
    }

    @Override
    public void save(Resume resume) {
        Object key = getKey(resume);
        if (!checkKey(key))
            subSave(key, resume);
        else
            throw new ExistStorageException(resume.getUuid());
    }

    @Override
    public Resume get(Resume resume) {
        Object key = getKeyIfResumeExist(resume);
        return subGet(key);
    }

    @Override
    public void delete(Resume resume) {
        Object key = getKeyIfResumeExist(resume);
        subDelete(key);
    }


    protected abstract List<Resume> getAll();

    protected abstract boolean checkKey(Object key);

    protected abstract Object getKey(Resume uuid);

    protected abstract void subUpdate(Object key, Resume resume);

    protected abstract Resume subGet(Object key);

    protected abstract void subSave(Object key, Resume resume);

    protected abstract void subDelete(Object key);
}
