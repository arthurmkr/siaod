package ru.mab.siaod.leetcode;

import java.util.Arrays;

/**
 * 238. Product of Array Except Self
 */
public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(productExceptSelf(new int[]{2, 3, 4, 5, 6})));
        System.out.println(Arrays.toString(productExceptSelf(new int[]{-1, 1, 0, -3, 3})));
        System.out.println(Arrays.toString(productExceptSelf(new int[]{-1, 1})));
    }

    public static int[] productExceptSelf(int[] nums) {
        int prefixProduct = 1;
        int suffixProduct = 1;
        int[] result = new int[nums.length];
        for(int i=0; i<nums.length; i++) {
            result[i] = prefixProduct;
            prefixProduct *= nums[i];
        }
        for(int i = nums.length-1; i>=0; i--) {
            result[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }
        return result;
    }
}
