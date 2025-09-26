package ru.mab.siaod.leetcode;

import java.util.Arrays;

/**
 * 66. Plus One
 */
public class PlusOne {
    public static void main(String[] args) {
        PlusOne alg = new PlusOne();
        System.out.println(Arrays.toString(alg.plusOne(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(alg.plusOne(new int[]{9, 9})));
        System.out.println(Arrays.toString(alg.plusOne(new int[]{9, 8})));
    }

    public int[] plusOne(int[] digits) {
        int carry = 1;

        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = carry + digits[i];
            carry = sum / 10;
            digits[i] = sum % 10;
        }

        if (carry == 0) {
            return digits;
        } else {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            System.arraycopy(digits, 0, res, 1, digits.length);
            return res;
        }
    }
}
