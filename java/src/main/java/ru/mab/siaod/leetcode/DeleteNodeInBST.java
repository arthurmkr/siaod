package ru.mab.siaod.leetcode;

import static ru.mab.siaod.leetcode.TreeNode.node;

public class DeleteNodeInBST {
    public static void main(String[] args) {
        TreeNode x = deleteNode(TreeNode.of(5, 3, 6, 2, 4, null, 7), 3);
        System.out.println(x);
        TreeNode x1 = deleteNode(TreeNode.of(5, 3, 6, 2, 4, null, 7), 0);
        System.out.println(x1);
        TreeNode x2 = deleteNode(
                node(3,
                        node(2),
                        node(5,
                                node(4),
                                node(10,
                                        node(8,
                                                node(7)),
                                        node(15)))), 5);
        System.out.println(x2);
    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        TreeNode prev = null;
        TreeNode cur = root;
        while (cur != null && cur.val != key) {
            prev = cur;
            if (key < cur.val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        if (cur == null) {
            return root;
        }

        if (cur.right != null && cur.left != null) {
            TreeNode rightNearest = cur.right;
            while (rightNearest.left != null) {
                rightNearest = rightNearest.left;
            }
            rightNearest.left = cur.left;
        }
        TreeNode replaceNode = cur.right == null ? cur.left : cur.right;
        if (prev == null) {
            root = replaceNode;
        } else if (prev.left == cur) {
            prev.left = replaceNode;
        } else if (prev.right == cur) {
            prev.right = replaceNode;
        }

        return root;
    }
}
