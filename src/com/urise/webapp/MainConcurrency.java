package com.urise.webapp;


public class MainConcurrency {
    private static final Object LOCK = new Object();
    private static final Object LOCK2 = new Object();

    public static void main(String[] args) {
        final MainConcurrency mainConcurrency = new MainConcurrency();

        /*DEAD LOCK*/
        Thread thread = new Thread(() -> {
            synchronized (mainConcurrency.LOCK) {
                System.out.println("Lock1");
                synchronized (mainConcurrency.LOCK2) {
                    System.out.println("Lock2");
//lock                   mainConcurrency.LOCK2.notify();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (mainConcurrency.LOCK2) {
                System.out.println("Lock2");
                synchronized (mainConcurrency.LOCK) {
                    System.out.println("Lock1");
                }
            }
        });

        thread.start();
        thread2.start();
    }
}
