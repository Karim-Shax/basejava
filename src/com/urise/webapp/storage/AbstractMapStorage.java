package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapStorage<K> extends AbstractStorage<K> {

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
    public int size() {
        return resumeMap.size();
    }

}
