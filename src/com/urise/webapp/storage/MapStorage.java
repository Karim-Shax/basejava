package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public Resume[] getAll() {
        return resumeMap.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return resumeMap.size();
    }

    @Override
    protected int getKey(String uuid) {
        return resumeMap.containsKey(uuid) ? 150 : -150;
    }

    @Override
    protected void subUpdate(Object uuid, Resume resume) {
        resumeMap.put((String) uuid, resume);
    }

    @Override
    protected Resume subGet(Object uuid) {
        return resumeMap.get(uuid);
    }

    @Override
    protected void subSave(Object uuid, Resume resume) {
        resumeMap.putIfAbsent((String) uuid, resume);
    }

    @Override
    protected void subDelete(Object uuid) {
        resumeMap.remove(uuid);
    }
}
