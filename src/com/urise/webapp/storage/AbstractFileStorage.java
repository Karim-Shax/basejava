package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class AbstractFileStorage {

    private final Path path;
    private List<String> paths;

    public AbstractFileStorage(String path) {
        this.path = Paths.get(path);
        this.paths = getFileTree(path);
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

    public void addWriteFile(Resume resume) {
        try {
            Path file = Files.createFile(Paths.get(path.toAbsolutePath().toString() + "/" + resume.getUuid() + ".txt"));
            Files.write(file, resume.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void removeFile(Resume resume) {
        try {
            Files.delete(Paths.get(path.toAbsolutePath().toString() + "/" + resume.getUuid() + ".txt"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void changeFile(Path path, Resume resume) {

    }
}
