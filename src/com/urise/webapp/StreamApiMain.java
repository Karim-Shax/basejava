package com.urise.webapp;


import java.util.*;
import java.util.stream.Collectors;

public class StreamApiMain {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 123; i++) {
            list.add(i);
        }
        System.out.println(minValue(new int[]{1, 1, 2, 2, 5, 3, 6, 9, 7, 4, 5, 1, 23, 6,}));
        System.out.println(oddOrEven(list));
    }

    public static int minValue(int[] values) {
        List<Integer> list = new ArrayList<>();
        Arrays.stream(values).filter(x -> x < 10).map((x) -> {
            int count = 0;
            for (int i : values) {
                if (i == x) {
                    count++;
                }
            }
            if (count == 1) {
                list.add(x);
            }
            return x;
        }).min();
        return list.stream().min((x, y) -> x - y).get();
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().reduce(0, Integer::sum);
        if (sum % 2 == 0) {
            return integers.stream().filter(x -> x % 2 != 0).collect(Collectors.toList());
        }
        return integers.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
    }
}
