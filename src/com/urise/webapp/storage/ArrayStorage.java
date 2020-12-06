package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void update(Resume resume) {
        int mark = checkList(resume.uuid);
        if (mark != -1) {
            storage[mark] = resume;
        } else {
            System.out.println("Error resume not found");
        }
    }

    public void save(Resume r) {
        int mark = checkList(r.uuid);
        if (mark == -1 && size < storage.length - 1) {
            storage[size++] = r;
        } else {
            System.out.println("Error resume is already on the list");
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Error resume not found");
        return null;
    }

    //Метод проверки на наличие резюме
    int checkList(String uuid) {
        int mark = -1;
        if (size == 0 || uuid == null) {
            return mark;
        }
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                mark = i;
            }
        }
        return mark;
    }

    public void delete(String uuid) {
        int mark = checkList(uuid);
        if (mark != -1) {
            storage[mark] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Error resume not found");
        }
    }

    public Resume[] getAll() {
        Resume[] allResume = Arrays.copyOf(storage, size);
        return allResume;
    }

    public int size() {
        return size;
    }
}
