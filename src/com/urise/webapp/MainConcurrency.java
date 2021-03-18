package com.urise.webapp;


public class MainConcurrency {

    public static void main(String[] args) {
        Object lock = new Object();
        Object lock2 = new Object();

        new Thread(() -> {
            try {
                deadLock(lock, lock2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                deadLock(lock2, lock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void deadLock(Object obj, Object obj2) throws InterruptedException {
        synchronized (obj) {
            obj.wait(100);
            System.out.println(Thread.currentThread().getName() + " blocked " + obj.toString() + ", waiting for release " + obj2.toString());
            synchronized (obj2) {
                obj.notify();
                System.out.println(Thread.currentThread().getName() + " blocked " + obj2.toString());
            }
        }
    }
}
