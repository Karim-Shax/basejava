package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    private final Map<Object, Resume> resumeMap = new HashMap<>();

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(this.resumeMap.values());
    }

    @Override
    public int size() {
        return this.resumeMap.size();
    }

    @Override
    protected Object getKey(Resume resume) {
        return this.resumeMap.containsKey(resume.getUuid()) ? resume.getUuid() : null;
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
    protected void subSave(Object uuid, Resume resume) {
        //здесь uuid всегда равен null
        this.resumeMap.putIfAbsent(resume.getUuid(), resume);
    }

    @Override
    protected void subDelete(Object uuid) {
        this.resumeMap.remove(uuid);
    }

    @Override
    protected boolean checkKey(Object key) {
        return key != null;
    }

}
