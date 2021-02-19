package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serialize.IOStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {

    private final File directory;
    private final IOStrategy strategy;

    protected FileStorage(File directory, IOStrategy strategy) {
        Objects.requireNonNull(directory, "directory must not be null");
        Objects.requireNonNull(strategy, "io type must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
        this.strategy = strategy;
    }


    @Override
    protected File getKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void subUpdate(File file, Resume r) {
        try {
            strategy.writeObj(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", r.getUuid());
        }
    }

    @Override
    protected boolean checkKey(File file) {
        return file.exists();
    }

    @Override
    protected void subSave(File file, Resume resume) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName());
        }
        subUpdate(file, resume);
    }

    @Override
    protected Resume subGet(File file) {
        try {
            return strategy.readObj(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName());
        }
    }

    @Override
    protected void subDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> list = new ArrayList<>(checkAndGetFileList().length);
        for (File file : checkAndGetFileList()) {
            list.add(subGet(file));
        }
        return list;
    }

    @Override
    public void clear() {
        for (File file : checkAndGetFileList()) {
            subDelete(file);
        }
    }

    @Override
    public int size() {
        return checkAndGetFileList().length;
    }

    private File[] checkAndGetFileList() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory read error", null);
        }
        return files;
    }
}