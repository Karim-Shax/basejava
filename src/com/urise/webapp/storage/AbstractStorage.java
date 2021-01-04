package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.*;

public abstract class AbstractStorage implements Storage {

    @Override
    public void clear() {
       subClear();
    }

    @Override
    public void update(Resume resume) {
      subUpdate(resume);
    }

    @Override
    public void save(Resume resume) {
      subSave(resume);
    }

    @Override
    public Resume get(String uuid) {
        return subGet(uuid);
    }

    @Override
    public void delete(String uuid) {
       subDelete(uuid);
    }

    @Override
    public Resume[] getAll() {
        return subGetAll();
    }

    @Override
    public int size() {
        return subSize();
    }

    protected abstract void subClear();
    protected abstract void subUpdate(Resume resume);
    protected abstract Resume subGet(String uuid);
    protected abstract void subSave(Resume resume);
    protected abstract void subDelete(String uuid);
    protected abstract Resume[] subGetAll();
    protected abstract int subSize();

}
