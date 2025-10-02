package ru.mab.siaod.leetcode;

/**
 * 724. Find Pivot Index
 */
public class FindPivotIndex {
    public static void main(String[] args) {
        System.out.println(pivotIndex(new int[]{1, 7, 3, 6, 5, 6}) == 3);
        System.out.println(pivotIndex(new int[]{1, 2, 3}) == -1);
        System.out.println(pivotIndex(new int[]{2, 1, -1}) == 0);
        System.out.println(pivotIndex(new int[]{-1, -1, -1, -1, -1, 0}) == 2);
    }

    public static int pivotIndex(int[] nums) {
        int[] sumR = new int[nums.length];

        for (int i = nums.length - 1, j = sumR.length - 2; i > 0; i--, j--) {
            sumR[j] = sumR[j + 1] + nums[i];
        }

        int i = 0;
        int sum = 0;
        do {
            if (sum == sumR[i]) {
                return i;
            }
            sum += nums[i];
            i++;
        } while (i < nums.length);

        return -1;
    }
}
