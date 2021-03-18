package com.urise.webapp;


public class MainConcurrency {

    public static void main(String[] args) {
        Object lock = new Object();
        Object lock2 = new Object();

        new Thread(() -> {
            deadLock(lock, lock2);
        }).start();
        new Thread(() -> {
            deadLock(lock2, lock);
        }).start();
    }

    public static void deadLock(Object obj, Object obj2) {
        synchronized (obj) {
            try {
                obj.wait(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " blocked " + obj.toString() + ", waiting for release " + obj2.toString());
            synchronized (obj2) {
                obj.notify();
                System.out.println(Thread.currentThread().getName() + " blocked " + obj2.toString());
            }
        }
    }
}
