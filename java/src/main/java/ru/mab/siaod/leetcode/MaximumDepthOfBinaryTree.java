package ru.mab.siaod.leetcode;

public class MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        System.out.println(maxDepth(TreeNode.of(3, 9, 20, null, null, 15, 7)) == 3);
        System.out.println(maxDepth(TreeNode.of(1, null, 2)) == 2);
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
