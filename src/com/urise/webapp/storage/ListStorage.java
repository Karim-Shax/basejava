package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;


public class ListStorage extends AbstractStorage {

    private final List<Resume> resumeList = new ArrayList<>();

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    public void subUpdate(Object index, Resume resume) {
        resumeList.set((int) index, resume);
    }

    @Override
    public void subSave(Object index, Resume resume) {
        resumeList.add(resume);
    }

    @Override
    public Resume subGet(Object index) {
        return resumeList.get((int) index);
    }

    @Override
    public void subDelete(Object index) {
        resumeList.remove((int) index);
    }

    @Override
    public List<Resume> getAll() {
        return resumeList;
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    protected Object getKey(Object uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (uuid.equals(resumeList.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean checkKey(Object key) {
        return (int) key >= 0;
    }
}
