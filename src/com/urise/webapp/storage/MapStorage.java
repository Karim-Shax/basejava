package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;


public class MapStorage extends AbstractMapStorage {

    @Override
    protected Object getKey(Object uuid) {
        return this.resumeMap.containsKey(uuid) ? uuid : null;
    }

    @Override
    protected void subUpdate(Object uuid, Resume resume) {
        this.resumeMap.put(String.valueOf(uuid), resume);
    }

    @Override
    protected Resume subGet(Object uuid) {
        return this.resumeMap.get(uuid);
    }

    @Override
    protected void subDelete(Object uuid) {
        this.resumeMap.remove(uuid);
    }
}
