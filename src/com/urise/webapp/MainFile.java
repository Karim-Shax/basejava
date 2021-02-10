package com.urise.webapp;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MainFile {
    public static void main(String[] args) {
        List<String> listFiles = getTree(new LinkedList<>(), new File("D:/tutorial"), new LinkedList());
        for (String s:listFiles){
            System.out.println(s);
        }
    }

    //рекурсивный метод который проходит по всем файлам и подпапкам, возвращает список строк путей к файлам (только файлам)
    //на вход принимает два пустых Linkedlist и файл корень по которому надо пройтись

    public static List<String> getTree(Queue<File> listFile, File file, List<String> str) {
        for (File k : file.listFiles()) {
            if (k.isDirectory()) {
                // здесь добавляется в очередь файл
                listFile.offer(k);
            } else {
                str.add(k.getAbsolutePath());
            }
        }
        if (!listFile.isEmpty()) {
            // здесь удаляется из очереди и передается дальше пока не закончатся директорий
            return getTree(listFile, listFile.poll(), str);
        } else {
            return str;
        }
    }
}
