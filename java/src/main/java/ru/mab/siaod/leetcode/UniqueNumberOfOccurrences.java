package ru.mab.siaod.leetcode;

import java.util.*;

/**
 * 1207. Unique Number of Occurrences
 */
public class UniqueNumberOfOccurrences {
    public static void main(String[] args) {
        System.out.println(uniqueOccurrences(new int[]{1, 2, 2, 1, 1, 3}) == true);
        System.out.println(uniqueOccurrences(new int[]{1, 2}) == false);
        System.out.println(uniqueOccurrences(new int[]{-3, 0, 1, -3, 1, 1, 1, -3, 10, 0}) == true);
    }

    public static boolean uniqueOccurrences(int[] arr) {
        if(arr.length == 1) {
            return true;
        }

        int shift = 1000;
        short[] counters = new short[shift * 2 + 1];
        for (int val : arr) {
            counters[val + shift]++;
        }

        Set<Short> unique = new HashSet<>(arr.length);
        for(int i = 0; i < counters.length; i++) {
            if(counters[i] == 0) {
                continue;
            }
            boolean add = unique.add(counters[i]);
            if(!add) {
                return false;
            }
        }
        return true;
    }

//    public static boolean uniqueOccurrences(int[] arr) {
//        Map<Integer, Integer> numbers = new HashMap<>();
//        for (int val : arr) {
//            numbers.put(val, numbers.getOrDefault(val, 0) + 1);
//        }
//
//        Set<Integer> unique = new HashSet<>();
//        for (Integer value : numbers.values()) {
//            boolean isExists = unique.add(value);
//
//            if (!isExists) {
//                return false;
//            }
//        }
//
//        return true;
//    }
}
