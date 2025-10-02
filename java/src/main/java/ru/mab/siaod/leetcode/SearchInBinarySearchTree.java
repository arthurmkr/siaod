package ru.mab.siaod.leetcode;

/**
 * 700. Search in a Binary Search Tree
 */
public class SearchInBinarySearchTree {
    public static void main(String[] args) {
        System.out.println(searchBST(TreeNode.of(4, 2, 7, 1, 3), 2).val == 2);
        System.out.println(searchBST(TreeNode.of(4, 2, 7, 1, 3), 5) == null);
    }

    public static TreeNode searchBST(TreeNode root, int val) {
        if(root == null) {
            return null;
        }

        if(root.val == val) {
            return root;
        }

        return root.val > val ? searchBST(root.left, val) : searchBST(root.right, val);
    }
}
