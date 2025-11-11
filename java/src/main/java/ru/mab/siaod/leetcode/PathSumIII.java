package ru.mab.siaod.leetcode;

import java.util.Map;
import java.util.TreeMap;

/**
 * 437. Path Sum III
 */
public class PathSumIII {
    public static void main(String[] args) {
        System.out.println(pathSum(TreeNode.of(10, 5, -3, 3, 2, null, 11, 3, -2, null, 1), 8) == 3);
        System.out.println(pathSum(TreeNode.of(8), 8) == 1);
        System.out.println(pathSum(null, 8) == 0);
        System.out.println(pathSum(TreeNode.of(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, null, 5, 1), 22) == 3);
        System.out.println(pathSum(TreeNode.of(1, -2, -3, 1, 3, -2, null, -1), 0) == 2); // 2
        System.out.println(pathSum(TreeNode.of(1, -2, -3, 1, 3, -2, null, -1), -1) == 4); // 4
        System.out.println(pathSum(
                TreeNode.node(1, null,
                        TreeNode.node(2, null,
                                TreeNode.node(3, null,
                                        TreeNode.node(4, null,
                                                TreeNode.node(5))))), 3) == 2);
        System.out.println(pathSum(
                TreeNode.node(1000000000,
                        TreeNode.node(1000000000,
                                TreeNode.node(294967296,
                                        TreeNode.node(1000000000,
                                                TreeNode.node(1000000000,
                                                        TreeNode.node(1000000000)))))), 0) == 0);
    }

//    public static int pathSum(TreeNode root, int targetSum) {
//        HashMap<Long, Integer> prefixSumCount = new HashMap<>();
//        prefixSumCount.put(0L, 1);
//        return dfs(root, 0L, targetSum, prefixSumCount);
//    }
//
//    private static int dfs(TreeNode node, long currSum, int target, HashMap<Long, Integer> prefixSumCount) {
//        if (node == null) {
//            return 0;
//        }
//
//        currSum += node.val;
//        int res = prefixSumCount.getOrDefault(currSum - target, 0);
//
//        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);
//
//        res += dfs(node.left, currSum, target, prefixSumCount);
//        res += dfs(node.right, currSum, target, prefixSumCount);
//        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
//
//        return res;
//    }

    public static int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        Map<Long, Integer> sums = new TreeMap<>();
        sums.put(0L, 1);

        return calc(root, sums, 0, targetSum);
    }

    private static int calc(TreeNode root, Map<Long, Integer> sums, long prevSum, int targetSum) {
        long curSum = prevSum + root.val;
        int res = sums.getOrDefault(curSum - targetSum, 0);

        sums.put(curSum, sums.getOrDefault(curSum, 0) + 1);

        if (root.left != null) {
            res += calc(root.left, sums, curSum, targetSum);
        }

        if (root.right != null) {
            res += calc(root.right, sums, curSum, targetSum);
        }

        sums.put(curSum, sums.get(curSum) - 1);
        return res;
    }
}
