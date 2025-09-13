package ru.mab.siaod.leetcode;

public class ThirdMaximumNumber {
    public static void main(String[] args) {
        System.out.println(thirdMax(new int[]{3, 2, 1}) == 1);
        System.out.println(thirdMax(new int[]{1, 2}) == 2);
        System.out.println(thirdMax(new int[]{1, 2, 2}) == 2);
        System.out.println(thirdMax(new int[]{2, 2, 3, 1}) == 1);
        System.out.println(thirdMax(new int[]{1, 1, 2}) == 2);
        System.out.println(thirdMax(new int[]{5, 2, 4, 1, 3, 6, 0}) == 4);
        System.out.println(thirdMax(new int[]{3, 3, 3, 3, 4, 3, 3, 3, 3}) == 4);
    }

    public static int thirdMax(int[] nums) {
        long max1 = Long.MIN_VALUE;
        long max2 = Long.MIN_VALUE;
        long max3 = Long.MIN_VALUE;
        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2 && num != max1) {
                max3 = max2;
                max2 = num;
            } else if (num > max3 && num != max1 && num != max2) {
                max3 = num;
            }
        }
        if (max3 == Long.MIN_VALUE) {
            return (int) max1;
        }
        return (int) max3;
    }
}
