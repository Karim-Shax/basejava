package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;


public abstract class AbstractStorage<K> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    private K getKeyIfResumeExist(String uuid) {
        K key = getKey(uuid);
        if (checkKey(key))
            return key;
        else
            LOG.warning("Resume " + uuid + " not exist");
        throw new NotExistStorageException(uuid);
    }

    @Override
    public List<Resume> getAllSortedList() {
        ArrayList<Resume> list = new ArrayList<>(getAll());
        Comparator<Resume> comparator = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
        list.sort(comparator);
        LOG.info("getAllSorted");
        return list;
    }

    @Override
    public void update(Resume resume) {
        subUpdate(getKeyIfResumeExist(resume.getUuid()), resume);
        LOG.info("Update " + resume.getUuid());
    }

    @Override
    public void save(Resume resume) {
        K key = getKey(resume.getUuid());
        if (!checkKey(key)) {
            subSave(key, resume);
            LOG.info("Save " + resume.getUuid());
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return subGet(getKeyIfResumeExist(uuid));
    }

    @Override
    public void delete(String uuid) {
        subDelete(getKeyIfResumeExist(uuid));
        LOG.info("Delete " + uuid);
    }


    protected abstract List<Resume> getAll();

    protected abstract boolean checkKey(K key);

    protected abstract K getKey(String uuid);

    protected abstract void subUpdate(K key, Resume resume);

    protected abstract Resume subGet(K key);

    protected abstract void subSave(K key, Resume resume);

    protected abstract void subDelete(K key);
}
