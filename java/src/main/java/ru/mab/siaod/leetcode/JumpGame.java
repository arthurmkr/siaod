package ru.mab.siaod.leetcode;

/**
 * 55. Jump Game
 */
public class JumpGame {
    public static void main(String[] args) {
        JumpGame alg = new JumpGame();

        System.out.println(alg.canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(alg.canJump(new int[]{3, 2, 1, 0, 4}));
    }

    public boolean canJump(int[] nums) {
        int l = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] + i >= l) {
                l = i;
            }
        }

        return nums[0] >= l;
    }
}
