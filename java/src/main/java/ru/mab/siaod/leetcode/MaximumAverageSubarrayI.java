package ru.mab.siaod.leetcode;

/**
 * 643. Maximum Average Subarray I
 */
public class MaximumAverageSubarrayI {
    public static void main(String[] args) {
        System.out.println(findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4) == 12.75);
        System.out.println(findMaxAverage(new int[]{5}, 1) == 5.);
        System.out.println(findMaxAverage(new int[]{0,4,0,3,2}, 1) == 4.);
    }

    public static double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        int i = 0;
        while (i < k) {
            sum += nums[i++];
        }

        int start = 0;
        int max = sum;

        while (i < nums.length) {
            sum = sum - nums[start] + nums[i];
            max = Math.max(sum, max);

            start++;
            i++;
        }

        return (double) max / k;
    }
}
