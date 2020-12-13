package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void sortedAndSave(Resume resume, int index) {
        index = Math.abs(index) - 1;
        for (int i = size; i > index; i--) {
            storage[i] = storage[i - 1];
        }
        storage[index] = resume;
    }

    @Override
    public void sortBeforeDelete(int index) {
        for (int i = index; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
    }

    @Override
    protected int checkId(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
