package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapStorage extends AbstractStorage {

    protected final Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(this.resumeMap.values());
    }

    @Override
    protected void subSave(Object uuid, Resume resume) {
        //здесь uuid всегда равен null
        resumeMap.putIfAbsent(resume.getUuid(), resume);
    }

    @Override
    public int size() {
        return this.resumeMap.size();
    }

    @Override
    protected boolean checkKey(Object key) {
        return key != null;
    }
}
