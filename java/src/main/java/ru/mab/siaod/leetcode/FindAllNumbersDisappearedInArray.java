package ru.mab.siaod.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 448. Find All Numbers Disappeared in an Array
 */
public class FindAllNumbersDisappearedInArray {
    public static void main(String[] args) {
        System.out.println(findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            if (nums[i] == nums[nums[i] - 1]) {
                i++;
            } else {
                int temp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[temp - 1] = temp;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                list.add(i + 1);
            }
        }
        return list;
    }
}
