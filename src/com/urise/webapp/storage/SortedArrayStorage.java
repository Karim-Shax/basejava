package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = Math.abs(checkId(resume.getUuid())); //индекс для вставки элемента
        if (!resume.equals(storage[index])) {           //если элемент не найден
            index--;
            if (size < storage.length - 1) {
                if (size == 0) {                        //массив пустой ставим на нулевой индекс
                    storage[0] = resume;
                } else {                                 //иначе отодвигаем объекты начиная с index до size
                    for (int i = size; i > index; i--) {
                        storage[i] = storage[i - 1];
                    }
                    storage[index] = resume;            //и вставляем resume в index
                }
                size++;
            } else {
                System.out.println("Storage overflowed");
            }
        } else {
            System.out.println("Error " + resume.getUuid() + " is already on the array");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = checkId(uuid);
        if (size != 0 && index != -1) {
            for (int i = index; i < size - 1; i++) {
                storage[i] = storage[i + 1];
            }
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Error " + uuid + "not found");
        }
    }

    @Override
    protected int checkId(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
