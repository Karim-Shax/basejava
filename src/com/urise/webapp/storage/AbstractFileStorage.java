package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class AbstractFileStorage extends AbstractStorage {


    private final Path root;
    private List<String> paths;

    public AbstractFileStorage(String root) {
        this.root = Paths.get(root);
        this.paths = getFileTree(root);
    }

    //метод который обходит папку и подпапки ищет и сохраняет файлы в список и возвращает список полных путей к файлам
    public static List<String> getFileTree(String root) {
        Queue<File> list = new LinkedList<>();
        File file = new File(root);
        List<String> str = new LinkedList<>();
        if (file.isDirectory()) {
            list.add(file);
        }
        while (!list.isEmpty()) {
            for (File k : Objects.requireNonNull(list.poll().listFiles())) {
                if (k.isDirectory()) {
                    list.offer(k);
                } else {
                    str.add(k.getAbsolutePath());
                }
            }
        }
        return str;
    }

    @Override
    protected List<Resume> getAll() {
        return null;
    }

    @Override
    protected boolean checkKey(Object key) {
        return false;
    }

    @Override
    protected Object getKey(String uuid) {
        return null;
    }

    @Override
    protected void subUpdate(Object key, Resume resume) {

    }

    @Override
    protected Resume subGet(Object key) {
        return null;
    }

    @Override
    protected void subSave(Object key, Resume resume) {
        try {
            Path file = Files.createFile(Paths.get(root.toAbsolutePath().toString() + "/" + ((Resume) key).getUuid() + ".txt"));
            Files.write(file, resume.toString().getBytes(StandardCharsets.UTF_8));
            paths.add(root.toAbsolutePath().toString() + "/" + ((Resume) key).getUuid() + ".txt");
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void subDelete(Object key) {
        try {
            Files.delete(Paths.get(root.toAbsolutePath().toString() + "/" + ((Resume) key).getUuid() + ".txt"));
            paths.remove(root.toAbsolutePath().toString() + "/" + ((Resume) key).getUuid() + ".txt");
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void clear() {
        try {
            Files.deleteIfExists(root);
            paths.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        return paths.size();
    }
}

