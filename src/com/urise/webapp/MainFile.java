package com.urise.webapp;

import java.io.*;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class MainFile {
    public static void main(String[] args) {
        printFileTree(new LinkedList<>(), new File("D:/base/basejava"));
    }

    public static void printFileTree(Queue<File> listFile, File file) {
        Objects.requireNonNull(listFile);
        System.out.println("\t\t" + "directory " + "\"" + file.getAbsolutePath() + "\"");
        for (File k : Objects.requireNonNull(file.listFiles())) {
            if (k.isDirectory()) {
                listFile.offer(k);
                System.out.println("\t\t\t" + "child directory " + "\"" + k.getName() + "\"");
            } else {
                System.out.println("\t\t\t" + "file " + k.getName());
            }
        }
        if (!listFile.isEmpty()) {
            printFileTree(listFile, listFile.poll());
        }
    }
}
