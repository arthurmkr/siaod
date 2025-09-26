package ru.mab.siaod.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PathSumII {
    public static void main(String[] args) {
        PathSumII alg = new PathSumII();
        System.out.println(alg.pathSum(TreeNode.of(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, null, 5, 1), 22));
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        subFunc(root, targetSum, new Stack<>(), res);

        return res;
    }

    private void subFunc(TreeNode node, int target, Stack<Integer> path, List<List<Integer>> res) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null && node.val == target) {
            List<Integer> curPath = new ArrayList<>(path);
            curPath.add(node.val);
            res.add(curPath);
            return;
        }

        path.push(node.val);
        subFunc(node.left, target - node.val, path, res);
        subFunc(node.right, target - node.val, path, res);
        path.pop();
    }
}
