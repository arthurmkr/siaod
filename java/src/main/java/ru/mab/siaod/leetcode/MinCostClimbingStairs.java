package ru.mab.siaod.leetcode;

/**
 * 746. Min Cost Climbing Stairs
 */
public class MinCostClimbingStairs {
    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs(new int[]{10, 15, 20}) == 15);
        System.out.println(minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}) == 6);
    }

    public static int minCostClimbingStairs(int[] cost) {
        int i = cost.length - 1;
        int[] mins = new int[2];

        while (i >= 0) {
            int min = Math.min(cost[i] + mins[0], cost[i] + mins[1]);
            mins[1] = mins[0];
            mins[0] = min;
            i--;
        }
        return Math.min(mins[0], mins[1]);
    }
}
