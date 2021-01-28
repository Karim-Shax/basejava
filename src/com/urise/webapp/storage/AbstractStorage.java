package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public abstract class AbstractStorage<K> implements Storage {

    private K getKeyIfResumeExist(String uuid) {
        K key = getKey(uuid);
        if (checkKey(key))
            return key;
        else
            throw new NotExistStorageException(uuid);
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
        subUpdate(getKeyIfResumeExist(resume.getUuid()), resume);
        System.out.println(resume.getUuid() + " updated");
    }

    @Override
    public void save(Resume resume) {
        K key = getKey(resume.getUuid());
        if (!checkKey(key))
            subSave(key, resume);
        else
            throw new ExistStorageException(resume.getUuid());
    }

    @Override
    public Resume get(String uuid) {
        return subGet(getKeyIfResumeExist(uuid));
    }

    @Override
    public void delete(String uuid) {
        subDelete(getKeyIfResumeExist(uuid));
    }


    protected abstract List<Resume> getAll();

    protected abstract boolean checkKey(K key);

    protected abstract K getKey(String uuid);

    protected abstract void subUpdate(K key, Resume resume);

    protected abstract Resume subGet(K key);

    protected abstract void subSave(K key, Resume resume);

    protected abstract void subDelete(K key);
}
