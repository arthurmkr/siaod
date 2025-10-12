package ru.mab.siaod.leetcode;

/**
 * 1004. Max Consecutive Ones III
 */
public class MaxConsecutiveOnesIII {
    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2) == 6);
        System.out.println(longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3) == 10);
    }

    public static int longestOnes(int[] nums, int k) {
        int start = 0;
        int zeroCnt = 0;
        int max = 0;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == 0) {
                if (zeroCnt == k) {
                    max = Math.max(max, i - start);

                    while (nums[start] != 0) {
                        start++;
                    }
                    start++;
                } else {
                    zeroCnt++;
                }
            }
            i++;
        }

        max = Math.max(max, i - start);
        return max;
    }
}
