package ru.mab.siaod.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 199. Binary Tree Right Side View
 */
public class BinaryTreeRightSideView {
    public static void main(String[] args) {
        System.out.println(rightSideView(TreeNode.of(1, 2, 3, null, 5, null, 4)).equals(List.of(1, 3, 4)));
        System.out.println(rightSideView(TreeNode.of(1, 2, 3, 4, null, null, null, 5)).equals(List.of(1, 3, 4, 5)));
        System.out.println(rightSideView(TreeNode.of(1, null, 3)).equals(List.of(1, 3)));
        System.out.println(rightSideView(TreeNode.of()).equals(List.of()));
    }

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    static void dfs(TreeNode root, List<Integer> res, int level) {
        if(root == null) {
            return;
        }

        if(res.size() <= level) {
            res.add(root.val);
        }

        dfs(root.right, res, level + 1);
        dfs(root.left, res, level + 1);
    }
}
