package ru.mab.siaod.leetcode;

/**
 * 136. Single Number
 */
public class SingleNumber {
    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2, 2, 1}) == 1);
        System.out.println(singleNumber(new int[]{4, 1, 2, 1, 2}) == 4);
    }

    public static int singleNumber(int[] nums) {
        int res = 0;
        for (int v : nums) {
            res = res ^ v;
        }

        return res;
    }
}
