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
    public void subUpdate(int index,Resume resume) {
            storage[index] = resume;
    }

    @Override
    public int subSize() {
        return size;
    }

    @Override
    public Resume subGet(int index) {
            return storage[index];
    }

    @Override
    public Resume[] subGetAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public void subSave(int index, Resume resume) {
            if (size < storage.length) {
                insertSave(resume, index);
                size++;
            } else {
                throw new StorageException("Storage overflow", resume.getUuid());
            }
    }

    @Override
    public void subDelete(int index) {
        delete(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected int getIndex(String uuid) {
        return checkId(uuid);
    }

    protected abstract int checkId(String uuid);

    protected abstract void delete(int index);

    protected abstract void insertSave(Resume resume, int index);
}
