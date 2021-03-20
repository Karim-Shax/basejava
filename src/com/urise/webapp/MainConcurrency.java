package com.urise.webapp;


import java.util.*;

public class MainConcurrency {

    public static void main(String[] args) {
        Object lock = new Object();
        Object lock2 = new Object();
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 105; i++) {
            list.add(i);
        }
        new Thread(() -> {
            deadLock(lock, lock2);
        }).start();
        new Thread(() -> {
            deadLock(lock2, lock);
        }).start();
    }

    public static void deadLock(Object obj, Object obj2) {
        synchronized (obj) {
            String name = Thread.currentThread().getName();
            String toStr = obj2.toString();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " blocked " + obj.toString() + ", waiting for release " + toStr);
            synchronized (obj2) {
                System.out.println(name + " blocked " + toStr);
            }
        }
    }


}
