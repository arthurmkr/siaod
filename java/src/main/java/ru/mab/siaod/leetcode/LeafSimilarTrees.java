package ru.mab.siaod.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 872. Leaf-Similar Trees
 */
public class LeafSimilarTrees {
    public static void main(String[] args) {
        System.out.println(leafSimilar(TreeNode.of(3, 5, 1, 6, 2, 9, 8, null, null, 7, 4),
                TreeNode.of(3, 5, 1, 6, 7, 4, 2, null, null, null, null, null, null, 9, 8)) == true);

        System.out.println(leafSimilar(TreeNode.of(1, 2, 3),
                TreeNode.of(1, 3, 2)) == false);
//
        System.out.println(leafSimilar(TreeNode.of(3, 5, 1, 6, 7, 4, 2, null, null, null,
                        null, null, null, 9, 11, null, null, null, null, null,
                        null, null, null, null, null, null, null, null, null, 8, 10),
                TreeNode.of(3, 5, 1, 6, 2, 9, 8, null, null, 7, 4)) == false);
    }

    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> arr1 = new ArrayList<>();
        fillLeafArray(root1, arr1);
        List<Integer> arr2 = new ArrayList<>();
        fillLeafArray(root2, arr2);

        return arr1.equals(arr2);
    }

    private static void fillLeafArray(TreeNode root, List<Integer> arr) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            arr.add(root.val);
            return;
        }

        fillLeafArray(root.left, arr);
        fillLeafArray(root.right, arr);
    }

//    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
//        Stack<TreeNode> stack = new Stack<>();
//        Queue<Integer> queue = new ArrayDeque<>();
//
//        stack.push(root1);
//        while (!stack.isEmpty()) {
//            TreeNode pop = stack.pop();
//            if (pop.left == null && pop.right == null) {
//                queue.add(pop.val);
//                continue;
//            }
//
//            if (pop.right != null) {
//                stack.push(pop.right);
//            }
//
//            if (pop.left != null) {
//                stack.push(pop.left);
//            }
//        }
//
//        stack.push(root2);
//        while (!stack.isEmpty() && !queue.isEmpty()) {
//            TreeNode pop = stack.pop();
//            if (pop.left == null && pop.right == null) {
//                int val1 = queue.poll();
//                if (pop.val != val1) {
//                    return false;
//                }
//                continue;
//            }
//
//            if (pop.right != null) {
//                stack.push(pop.right);
//            }
//
//            if (pop.left != null) {
//                stack.push(pop.left);
//            }
//        }
//
//        return queue.isEmpty() && stack.isEmpty();
//    }
}
