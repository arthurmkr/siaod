package ru.mab.siaod.leetcode;

/**
 * 1448. Count Good Nodes in Binary Tree
 */
public class CountGoodNodesInBinaryTree {
    public static void main(String[] args) {
        System.out.println(goodNodes(TreeNode.of(3, 1, 4, 3, null, 1, 5)) == 4);
        System.out.println(goodNodes(TreeNode.of(3, 3, null, 4, 2)) == 3);
        System.out.println(goodNodes(TreeNode.of(1)) == 1);
    }

    public static int goodNodes(TreeNode root) {
        return subFunc(root, Integer.MIN_VALUE);
    }

    static int subFunc(TreeNode node, int max) {
        if (node == null) {
            return 0;
        }

        int cnt = 0;
        if (node.val >= max) {
            cnt++;
        }

        int newMax = Math.max(node.val, max);
        cnt += subFunc(node.left, newMax);
        cnt += subFunc(node.right, newMax);

        return cnt;
    }
}
