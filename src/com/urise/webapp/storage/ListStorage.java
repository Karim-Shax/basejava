package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;


public class ListStorage extends AbstractStorage {

    private List<Resume> resumeList = new ArrayList<>();

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    public void subUpdate(int index, Resume resume) {
        resumeList.set(index, resume);
    }

    @Override
    public void subSave(int index, Resume resume) {
        resumeList.add(resume);
    }

    @Override
    public Resume subGet(int index) {
        return resumeList.get(index);
    }

    @Override
    public void subDelete(int index) {
        resumeList.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return resumeList.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (uuid.equals(resumeList.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
