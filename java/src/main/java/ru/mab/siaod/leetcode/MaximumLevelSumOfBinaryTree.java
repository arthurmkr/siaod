package ru.mab.siaod.leetcode;

/**
 * 1161. Maximum Level Sum of a Binary Tree
 */
public class MaximumLevelSumOfBinaryTree {
    public static void main(String[] args) {
        System.out.println(maxLevelSum(TreeNode.of(1, 7, 0, 7, -8, null, null)) == 2);
    }


    public static int maxLevelSum(TreeNode root) {
        int[] sums = new int[10000];

        int levels = calc(root, 0, sums);
        int max = Integer.MIN_VALUE;
        int maxLevel = 0;

        for (int i = 0; i < levels; i++) {
            if (sums[i] > max) {
                maxLevel = i + 1;
                max = sums[i];
            }
        }

        return maxLevel;
    }

    private static int calc(TreeNode root, int level, int[] sums) {
        if (root == null) {
            return level;
        }
        sums[level] += root.val;

        return Math.max(calc(root.left, level + 1, sums), calc(root.right, level + 1, sums));
    }
}
