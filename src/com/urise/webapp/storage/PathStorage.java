package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serialize.IOStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {

    private final Path directory;
    private final IOStrategy strategy;

    protected PathStorage(String dir, IOStrategy strategy) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        Objects.requireNonNull(strategy, "io type must not be null");
        this.strategy = strategy;
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    protected Path getKey(String uuid) {
        return directory.resolve(uuid);
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
        return checkAndGetFileStream().map(this::subGet).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        checkAndGetFileStream().forEach(this::subDelete);
    }

    @Override
    public int size() {
        return (int) checkAndGetFileStream().count();
    }

    private Stream<Path> checkAndGetFileStream() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", null);
        }
    }
}