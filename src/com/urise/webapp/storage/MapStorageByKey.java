package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class MapStorageByKey extends AbstractMapStorage<Resume> {
    @Override
    protected Resume getKey(String uuid) {
        return resumeMap.get(uuid) != null ? resumeMap.get(uuid) : new Resume(uuid, uuid);
    }

    @Override
    protected void subUpdate(Resume key, Resume resume) {
        resumeMap.put(key.getUuid(), resume);
    }

    @Override
    protected Resume subGet(Resume key) {
        return key;
    }

    @Override
    protected void subSave(Resume key, Resume resume) {
        resumeMap.putIfAbsent(key.getUuid(), resume);
    }

    @Override
    protected void subDelete(Resume key) {
        resumeMap.remove(key.getUuid());
    }

    @Override
    protected boolean checkKey(Resume key) {
        return resumeMap.containsKey(key.getUuid());
    }
}
