package ru.mab.siaod.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2215. Find the Difference of Two Arrays
 */
public class FindTheDifferenceOfTwoArrays {
    public static void main(String[] args) {
        System.out.println(findDifference(new int[]{1, 2, 3}, new int[]{2, 4, 6}));
    }

    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        int[] arr = new int[2001];
        for (int v : nums1) {
            arr[v + 1000] = 1;
        }
        for (int v : nums2) {
            int i = v + 1000;
            arr[i] = arr[i] | 2;
        }

        List<Integer> res1 = new ArrayList<>();
        List<Integer> res2 = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 2) {
                res2.add(i - 1000);
            } else if (arr[i] == 1) {
                res1.add(i - 1000);

            }
        }

        return List.of(res1, res2);
    }
}
