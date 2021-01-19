package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorageByFullName extends MapStorage {

    protected final Map<Resume, Resume> resumeMap = new HashMap<>();

    @Override
    public void clear() {
        this.resumeMap.clear();
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(this.resumeMap.values());
    }

    @Override
    protected Object getKey(Resume resume) {
        return this.resumeMap.containsKey(resume) ? resume : null;
    }

    @Override
    protected void subUpdate(Object key, Resume resume) {
        this.resumeMap.put((Resume) key, resume);
    }

    @Override
    protected void subSave(Object key, Resume resume) {
        this.resumeMap.putIfAbsent(resume, resume);
    }

    @Override
    protected void subDelete(Object uuid) {
        this.resumeMap.remove(uuid);
    }

    @Override
    public int size() {
        return this.resumeMap.size();
    }

    @Override
    protected Resume subGet(Object uuid) {
        return this.resumeMap.get(uuid);
    }

    @Override
    protected boolean checkKey(Object key) {
        return key != null;
    }
}
