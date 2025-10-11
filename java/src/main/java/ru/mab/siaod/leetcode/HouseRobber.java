package ru.mab.siaod.leetcode;

/**
 * 198. House Robber
 */
public class HouseRobber {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{1,2,3,1}) == 4);
        System.out.println(rob(new int[]{2,7,9,3,1}) == 12);
    }

    public static int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        nums[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            nums[i] = Math.max(nums[i - 1], nums[i] + nums[i - 2]);
        }

        return nums[nums.length - 1];
    }
}
