package ru.mab.siaod.leetcode;

import lombok.val;

/**
 * 101. Symmetric Tree
 */
public class SymmetricTree {
    public static void main(String[] args) {
        SymmetricTree alg = new SymmetricTree();
        System.out.println(alg.isSymmetric(TreeNode.of(1,2,2,3,4,4,3)) == true);
        System.out.println(alg.isSymmetric(TreeNode.of(1,2,2,null,3,null,3)) == false);
    }

    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    public boolean check(TreeNode left, TreeNode right) {
        if(left == null && right == null) {
            return true;
        }

        if(left == null || right == null || left.val != right.val) {
            return false;
        }

        return (left.val != right.val) && check(left.left, right.right) && check(left.right, right.left);
    }
}
