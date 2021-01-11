package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> resumeMap = new HashMap<>();

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
    protected int getIndex(String uuid) {
        return resumeMap.containsKey(uuid) ? 1 : -1;
    }

    @Override
    protected void subUpdate(int index, Resume resume) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume subGet(int index, String uuid) {
        return resumeMap.get(uuid);
    }

    @Override
    protected void subSave(int index, Resume resume) {
        resumeMap.putIfAbsent(resume.getUuid(), resume);
    }

    @Override
    protected void subDelete(int index, String uuid) {
        resumeMap.remove(uuid);
    }
}
