package ru.mab.siaod.leetcode;

import java.util.Stack;

/**
 * 98. Validate Binary Search Tree
 */
public class ValidateBinarySearchTree {
    public static void main(String[] args) {
//        TreeNode root = TreeNode.of(2, 1, 3);
        ValidateBinarySearchTree alg = new ValidateBinarySearchTree();

//        System.out.println(alg.isValidBST(root));
        System.out.println(alg.isValidBST(TreeNode.of(-2147483648)));
    }

    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<Boolean> stackDirection = new Stack<>();

        TreeNode cur;
        stack.push(root);
        stackDirection.push(true);
        int max = Integer.MIN_VALUE;
        boolean first = true;
        while (!stack.isEmpty()) {
            cur = stack.pop();
            boolean dir = stackDirection.pop();
            if(dir) {
                while (cur.left != null) {
                    stack.push(cur);
                    stackDirection.push(false);
                    cur = cur.left;
                }
            }

            if(cur.val > max || (cur.val == Integer.MIN_VALUE && first)) {
                max = cur.val;
                first = false;
            } else {
                return false;
            }

            if(cur.right != null) {
                stack.push(cur.right);
                stackDirection.push(true);
            }
        }


        return true;
    }

//    public boolean isValidBST(TreeNode root) {
//        Stack<TreeNode> stack = new Stack<>();
//        Stack<Boolean> stackDirection = new Stack<>();
//
//        TreeNode cur;
//        stack.push(root);
//        stackDirection.push(true);
//        int max = Integer.MIN_VALUE;
//        boolean first = true;
//        while (!stack.isEmpty()) {
//            cur = stack.pop();
//            boolean dir = stackDirection.pop();
//            if(dir) {
//                while (cur.left != null) {
//                    stack.push(cur);
//                    stackDirection.push(false);
//                    cur = cur.left;
//                }
//            }
//
//            if(cur.val > max || (cur.val == Integer.MIN_VALUE && first)) {
//                max = cur.val;
//                first = false;
//            } else {
//                return false;
//            }
//
//            if(cur.right != null) {
//                stack.push(cur.right);
//                stackDirection.push(true);
//            }
//        }
//
//
//        return true;
//    }
}
