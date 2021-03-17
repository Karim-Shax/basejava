package com.urise.webapp;


public class MainConcurrency {

    public static void main(String[] args) {
        Object lock = new Object();
        Object lock2 = new Object();
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                deadLock(lock, lock2);
            }).start();
            new Thread(() -> {
                deadLock(lock2, lock);
            }).start();
        }
    }

    public static void deadLock(Object obj, Object obj2) {
        synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + " захватил " + obj.toString() + ", ждет освобождения " + obj2.toString());
            synchronized (obj2) {
                System.out.println(Thread.currentThread().getName() + " захватил " + obj2.toString());
            }
        }
    }
}
