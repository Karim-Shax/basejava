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
        System.out.println(minValue(new int[]{10, 11, 9, 5, 6, 3, 2, 7}));
        System.out.println(oddOrEven(list));
       /* new Thread(() -> {
            deadLock(lock, lock2);
        }).start();
        new Thread(() -> {
            deadLock(lock2, lock);
        }).start();*/
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

    public static int minValue(int[] values) {
        Optional<Integer> ar = Arrays.stream(values).boxed().filter(x -> x < 10)
                .distinct().min(Comparator.comparingInt(x -> x));
        return ar.get();
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        int sum = integers.stream().peek(x -> {
            if (x % 2 == 0) {
                even.add(x);
            } else {
                odd.add(x);
            }
        }).reduce(Integer::sum).get();

        if (sum % 2 == 0) {
            System.out.println("sum is even=" + sum);
            return odd;
        } else {
            System.out.println("sum is odd=" + sum);
            return even;
        }
    }
}
