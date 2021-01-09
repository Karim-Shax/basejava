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
        Resume[] resumes = getAll();
        for (int i = 0; i < resumes.length; i++) {
            if (resumes[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void subUpdate(int index, Resume resume) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume subGet(int index) {
        Resume[] resumes = getAll();
        return resumes[index];
    }

    @Override
    protected void subSave(int index, Resume resume) {
        resumeMap.putIfAbsent(resume.getUuid(), resume);
    }

    @Override
    protected void subDelete(int index) {
        resumeMap.remove(subGet(index).getUuid());
    }
}
