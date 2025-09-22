package ru.mab.siaod.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 173. Binary Search Tree Iterator
 */
public class BSTIterator {
    List<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new ArrayList<>();
        fillStack(root);
    }

    private void fillStack(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            stack.add(cur);
            cur = cur.left;
        }
    }

    public int next() {
        TreeNode cur = stack.remove(stack.size()-1);

        if (cur.right != null) {
            fillStack(cur.right);
        }

        return cur.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
