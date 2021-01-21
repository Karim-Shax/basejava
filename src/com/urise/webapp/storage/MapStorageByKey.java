package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class MapStorageByKey extends AbstractMapStorage {
    // getKey возвращает резюме далее во всех остальных методах
    // первый аргумент key это Resume
    @Override
    protected Object getKey(String uuid) {
        //если нет резюме, то создается новое фейковое с целью передать uuid
        //иначе просто get(uuid) возвращает null ошибка в checkKey или save
        return resumeMap.get(uuid) != null ? resumeMap.get(uuid) : new Resume(uuid, uuid);
    }

    @Override
    protected void subUpdate(Object key, Resume resume) {
        resumeMap.put(((Resume) key).getUuid(), resume);
    }

    @Override
    protected Resume subGet(Object key) {
        return (Resume) key;
    }

    @Override
    protected void subSave(Object key, Resume resume) {
        resumeMap.putIfAbsent(((Resume) key).getUuid(), resume);
    }

    @Override
    protected void subDelete(Object key) {
        resumeMap.remove(((Resume) key).getUuid());
    }

    @Override
    protected boolean checkKey(Object key) {
        return resumeMap.containsKey(((Resume) key).getUuid());
    }
}
