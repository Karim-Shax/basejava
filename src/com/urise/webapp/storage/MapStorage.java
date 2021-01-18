package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    protected final Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public List<Resume> getAllSortedList() {
        ArrayList<Resume> list = new ArrayList<>(resumeMap.values());
        Collections.sort(list, new ResumeComparatorByFullName().thenComparing(new ResumeCompareByUUid()));
        return list;
    }

    @Override
    public int size() {
        return resumeMap.size();
    }

    @Override
    protected Object getKey(String uuid) {
        return resumeMap.containsKey(uuid) ? uuid : null;
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
        //здесь uuid всегда равен null
        resumeMap.putIfAbsent(resume.getUuid(), resume);
    }

    @Override
    protected void subDelete(Object uuid) {
        resumeMap.remove(uuid);
    }

    @Override
    protected boolean checkKey(Object key) {
        return key != null;
    }

}
