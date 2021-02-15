package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AbstractPathStorage extends AbstractStorage<Path> {

    private Path directory;
    private IOStrategy strategy;

    protected AbstractPathStorage(String dir, IOStrategy strategy) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        Objects.requireNonNull(strategy, "io type must not be null");
        this.strategy = strategy;
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::subDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        String[] list = directory.toFile().list();
        if (list == null) {
            throw new StorageException("Directory read error", null);
        }
        return list.length;
    }

    @Override
    protected Path getKey(String uuid) {
        return new File(directory.toFile(), uuid).toPath();
    }

    @Override
    protected void subUpdate(Path path, Resume r) {
        try {
            strategy.writeObj(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", r.getUuid());
        }
    }

    @Override
    protected boolean checkKey(Path path) {
        return Files.exists(path);
    }

    @Override
    protected void subSave(Path path, Resume r) {
        Paths.get(directory.resolve(path).toUri());
        subUpdate(path, r);
    }

    @Override
    protected Resume subGet(Path path) {
        try {
            return strategy.readObj(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error", path.toFile().getName());
        }
    }

    @Override
    protected void subDelete(Path path) {
        try {
            if (!Files.deleteIfExists(path)) {
                throw new StorageException("Path delete error", path.toFile().getName());
            }
        } catch (IOException e) {
            throw new StorageException(e.getMessage(), path.toString());
        }
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> list = new ArrayList<>();
        try {
            list = Files.list(directory).map(this::subGet).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
        return list;
    }
}