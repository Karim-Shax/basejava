package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class MapStorageByFullName extends MapStorage {

    @Override
    protected Object getKey(String uuid) {
        for (Resume resume:resumeMap.values()){
            if (uuid.equals(resume.getUuid())){
                return resume;
            }
        }
        return null;
    }

    @Override
    protected void subUpdate(Object uuid, Resume resume) {
        Resume resume1=(Resume)uuid;
        this.resumeMap.put(resume1.getUuid(), resume);
    }

    @Override
    protected Resume subGet(Object uuid) {
        Resume resume1=(Resume)uuid;
        return this.resumeMap.get(resume1.getUuid());
    }

    @Override
    protected void subSave(Object uuid, Resume resume) {
        //здесь uuid всегда равен null
        this.resumeMap.putIfAbsent(resume.getUuid(), resume);
    }

    @Override
    protected void subDelete(Object uuid) {
        Resume resume1=(Resume)uuid;
        this.resumeMap.remove(resume1.getUuid());
    }

}
