package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;


public class MapStorage extends AbstractMapStorage {


    @Override
    protected Object getKey(String uuid) {
        return uuid;
    }

    @Override
    protected void subUpdate(Object uuid, Resume resume) {
        resumeMap.put(String.valueOf(uuid), resume);
    }

    @Override
    protected Resume subGet(Object uuid) {
        return resumeMap.get(uuid);
    }

    @Override
    protected void subSave(Object uuid, Resume resume) {
        resumeMap.putIfAbsent(String.valueOf(uuid), resume);
    }

    @Override
    protected void subDelete(Object uuid) {
        resumeMap.remove(uuid);
    }

    @Override
    protected boolean checkKey(Object key) {
        return resumeMap.containsKey(key);
    }

}
