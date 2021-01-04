package com.urise.webapp.storage;


import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;


public class ListStorage extends AbstractStorage {

    private List<Resume> resumeList = new ArrayList<>();

    @Override
    public void subClear() {
        resumeList.clear();
    }

    @Override
    public void subUpdate(Resume resume) {
        if (resumeList.contains(resume)) {
            int tmp = 0;
            for (int i = 0; i < resumeList.size(); i++) {
                if (resumeList.get(i).getUuid().equals(resume.getUuid())) {
                    tmp = i;
                    break;
                }
            }
            resumeList.set(tmp, resume);
        } else {
            throw new NotExistStorageException("resume not exist");
        }
    }

    @Override
    public void subSave(Resume resume) {
        if (!resumeList.contains(resume)) {
            resumeList.add(resume);
        } else {
            throw new ExistStorageException("Resume " + resume.getUuid() + " already exist");
        }
    }

    @Override
    public Resume subGet(String uuid) {
        for (Resume resume1 : resumeList) {
            if (resume1.getUuid().equals(uuid)) {
                return resume1;
            }
        }
        throw new NotExistStorageException("resume not exist");
    }

    @Override
    public void subDelete(String uuid) {
        if (resumeList.contains(new Resume(uuid))) {
            resumeList.remove(new Resume(uuid));
        } else {
            throw new NotExistStorageException("Resume" + uuid + " not exist");
        }
    }

    @Override
    public Resume[] subGetAll() {
        return resumeList.toArray(new Resume[0]);
    }

    @Override
    public int subSize() {
        return resumeList.size();
    }
}
