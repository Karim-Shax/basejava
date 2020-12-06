package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = checkId(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("Error " + resume.getUuid() + " not found");
        }
    }

    public void save(Resume r) {
        int index = checkId(r.getUuid());
        if (index == -1 && size < storage.length - 1) {
            storage[size++] = r;
        } else if (size == storage.length - 1) {
            System.out.println("Array overflowed");
        } else if (index != -1) {
            System.out.println("Error " + r.getUuid() + " is already on the array");
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Error " + uuid + " not found");
        return null;
    }

    //Метод проверки на наличие резюме
    int checkId(String uuid) {
        int index = -1;
        if (size == 0 || uuid == null) {
            return index;
        }
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                index = i;
            }
        }
        return index;
    }

    public void delete(String uuid) {
        int index = checkId(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Error " + uuid + " not found");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
