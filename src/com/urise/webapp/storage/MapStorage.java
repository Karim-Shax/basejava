package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;


public class MapStorage extends AbstractMapStorage<String> {

    @Override
    protected String getKey(String uuid) {
        return uuid;
    }

    @Override
    protected void subUpdate(String uuid, Resume resume) {
        resumeMap.put(uuid, resume);
    }

    @Override
    protected Resume subGet(String uuid) {
        return resumeMap.get(uuid);
    }

    @Override
    protected void subSave(String uuid, Resume resume) {
        resumeMap.putIfAbsent(uuid, resume);
    }

    @Override
    protected void subDelete(String uuid) {
        resumeMap.remove(uuid);
    }

    @Override
    protected boolean checkKey(String key) {
        return resumeMap.containsKey(key);
    }

}
