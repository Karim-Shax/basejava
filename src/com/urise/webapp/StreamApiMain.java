package com.urise.webapp;


import java.util.*;
import java.util.stream.Collectors;


public class StreamApiMain {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 123; i++) {
            list.add(i);
        }
        System.out.println(minValue(new int[]{1, 1, 2, 3, 6, 5, 4, 8, 8, 9, 8}));
        System.out.println(oddOrEven(list));
    }

    public static int minValue(int[] values) {
        return Arrays.stream(values).filter(x -> x < 10).distinct().reduce((x, y) -> {
            x = x * 10 + y;
            return x;
        }).getAsInt();
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().reduce(0, Integer::sum);
        if (sum % 2 == 0) {
            return integers.stream().filter(x -> x % 2 != 0).collect(Collectors.toList());
        }
        return integers.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
    }
}
