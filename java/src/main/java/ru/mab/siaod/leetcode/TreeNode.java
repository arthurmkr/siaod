package ru.mab.siaod.leetcode;


import java.util.Objects;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode of(Integer... nodes) {
        return build(nodes, 0);
    }

    public static TreeNode node(int val, TreeNode... nodes) {
        return new TreeNode(val, nodes.length > 0 ? nodes[0] : null, nodes.length > 1 ? nodes[1] : null);
    }


    public static TreeNode build(Integer[] vals, int rootIndex) {
        if (rootIndex >= vals.length) {
            return null;
        }
        if (vals[rootIndex] == null) {
            return null;
        }

        TreeNode left = build(vals, rootIndex * 2 + 1);
        TreeNode right = build(vals, rootIndex * 2 + 2);
        return new TreeNode(vals[rootIndex],
                left,
                right);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val && Objects.equals(left, treeNode.left) && Objects.equals(right, treeNode.right);
    }

    @Override
    public int hashCode() {
        int result = val;
        result = 31 * result + Objects.hashCode(left);
        result = 31 * result + Objects.hashCode(right);
        return result;
    }
}
