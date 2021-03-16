package com.urise.webapp;


public class MainConcurrency {

    public static void main(String[] args) {
        Object lock = new Object();
        Object lock2 = new Object();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2) {
                        lock.notify();
                    }
                }

            }).start();
            new Thread(() -> {
                synchronized (lock2) {
                    try {
                        lock2.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock) {
                        lock2.notify();
                    }
                }
            }).start();
        }
    }
}
