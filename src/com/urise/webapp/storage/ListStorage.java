package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;


public class ListStorage extends AbstractStorage<Integer> {

    private final List<Resume> resumeList = new ArrayList<>();

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    public void subUpdate(Integer index, Resume resume) {
        resumeList.set(index, resume);
    }

    @Override
    public void subSave(Integer index, Resume resume) {
        resumeList.add(resume);
    }

    @Override
    public Resume subGet(Integer index) {
        return resumeList.get(index);
    }

    @Override
    public void subDelete(Integer index) {
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
    protected Integer getKey(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (uuid.equals(resumeList.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean checkKey(Integer key) {
        return key >= 0;
    }
}
