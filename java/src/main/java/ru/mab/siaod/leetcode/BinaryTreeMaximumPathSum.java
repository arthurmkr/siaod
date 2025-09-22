package ru.mab.siaod.leetcode;

/**
 * 124. Binary Tree Maximum Path Sum
 */
public class BinaryTreeMaximumPathSum {
    final int MIN_VALUE = -1001;

    public static void main(String[] args) {
        BinaryTreeMaximumPathSum alg = new BinaryTreeMaximumPathSum();
//        System.out.println(alg.maxPathSum(TreeNode.of(1, 2, 3)));
//        System.out.println(alg.maxPathSum(TreeNode.of(-10,9,20,null,null,15,7)));
        System.out.println(alg.maxPathSum(TreeNode.of(-3)));
    }

    public int maxPathSum(TreeNode root) {
        int[] globalMax = new int[]{MIN_VALUE};
        subMaxPathSum(root, globalMax);
        return globalMax[0];
    }

    private int subMaxPathSum(TreeNode root, int[] globalMax) {
        if (root == null) {
            return MIN_VALUE;
        }

        int leftMax = subMaxPathSum(root.left, globalMax);
        int rightMax = subMaxPathSum(root.right, globalMax);

        int curMax = Math.max(
                Math.max(root.val, root.val + leftMax),
                root.val + rightMax);
        int max = Math.max(curMax, root.val + leftMax + rightMax);

        if (max > globalMax[0]) {
            globalMax[0] = max;
        }
        return curMax;
    }
}
