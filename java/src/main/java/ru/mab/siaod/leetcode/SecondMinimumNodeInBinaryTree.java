package ru.mab.siaod.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 671. Second Minimum Node In a Binary Tree
 */
public class SecondMinimumNodeInBinaryTree {

    int firstMin;
    Integer secondMin;

    public static void main(String[] args) {
        Map<Long, String> map = new HashMap<>(Map.of(1L, ""));

        for(Long key : map.keySet()) {
            map.remove(key);
        }
        System.out.println(Integer.toBinaryString(-(1 << 4)));
        System.out.println(Integer.toBinaryString(~((1 << 4) - 1)));
        System.out.println(Integer.toBinaryString(~(-1 >>> (32-4))));
        SecondMinimumNodeInBinaryTree alg = new SecondMinimumNodeInBinaryTree();

//        System.out.println(alg.findSecondMinimumValue(TreeNode.of(2, 2, 5, null, null, 5, 7)) == 5);
//        System.out.println(alg.findSecondMinimumValue(TreeNode.of(1,1,3,1,1,3,4,3,1,1,8)) == 3);
//        System.out.println(alg.findSecondMinimumValue(TreeNode.of(1, 1, 3, 1, 1, 3, 4, 3, 1, 1, 1, 3, 8, 4, 8, 3, 3, 1, 6, 2, 1)) == 2);
        System.out.println(alg.findSecondMinimumValue(TreeNode.of(2, 2, 2147483647)) == 2147483647);
    }

    public int findSecondMinimumValue(TreeNode root) {
        firstMin = root.val;
        subFind(root);
        return secondMin == null ? -1 : secondMin;
    }

    private void subFind(TreeNode node) {
        if (node == null) {
            return;
        }

        if (node.val > firstMin) {
            secondMin = secondMin != null ? Math.min(node.val, secondMin) : node.val;
            return;
        }

        subFind(node.left);
        subFind(node.right);
    }
}
