package ru.mab.siaod.leetcode;

import java.util.Arrays;

/**
 * 338. Counting Bits
 */
public class CountingBits {
    public static void main(String[] args) {
        System.out.println(Arrays.equals(countBits(2), new int[]{0, 1, 1}));
        System.out.println(Arrays.equals(countBits(5), new int[]{0, 1, 1, 2, 1, 2}));
    }

    public static int[] countBits(int n) {
        int[] counts = new int[n + 1];
        int mask = 1;

        int i = 1;
        while (i <= n) {
            for (int j = 0; j < mask && i <= n; j++, i++) {
                counts[i] = 1 + counts[j];
            }

            mask *= 2;
        }

        return counts;
    }
}
