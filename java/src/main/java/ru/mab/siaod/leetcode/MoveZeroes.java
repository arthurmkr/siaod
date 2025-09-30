package ru.mab.siaod.leetcode;

import java.util.Arrays;

/**
 * 283. Move Zeroes
 */
public class MoveZeroes {
    public static void main(String[] args) {
        test(new int[]{0, 1, 0, 3, 12});
        test(new int[]{0});
    }

    private static void test(int[] nums) {
        MoveZeroes alg = new MoveZeroes();
        alg.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void moveZeroes(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                nums[i++] = nums[j];
            }
        }

        while (i < nums.length) {
            nums[i++] = 0;
        }
    }
}
