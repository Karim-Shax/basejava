package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class MapStorageByKeyObject extends AbstractMapStorage {

    @Override
    protected Object getKey(Object uuid) {
        return resumeMap.containsKey(uuid) ? resumeMap.get(uuid) : null;
    }

    @Override
    protected void subUpdate(Object Obj, Resume resume) {
        Resume resume1 = (Resume) Obj;
        resumeMap.put(resume1.getUuid(), resume);
    }

    @Override
    protected Resume subGet(Object uuid) {
        return (Resume) uuid;
    }


    @Override
    protected void subDelete(Object uuid) {
        Resume resume1 = (Resume) uuid;
        resumeMap.remove(resume1.getUuid());
    }
}
