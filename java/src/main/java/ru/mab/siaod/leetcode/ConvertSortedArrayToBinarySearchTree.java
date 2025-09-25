package ru.mab.siaod.leetcode;

/**
 * 108. Convert Sorted Array to Binary Search Tree
 */
public class ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args) {
        ConvertSortedArrayToBinarySearchTree alg = new ConvertSortedArrayToBinarySearchTree();

        TreeNode x = alg.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        TreeNode x2 = alg.sortedArrayToBST(new int[]{1, 3});
        TreeNode x3 = alg.sortedArrayToBST(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
        System.out.println(x);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return subFunc(0, nums.length, nums);
    }

    private TreeNode subFunc(int l, int r, int[] nums) {
        if (l == r) {
            return null;
        }
        int mid = (r >> 1) + (l >> 1);

        return new TreeNode(nums[mid],
                subFunc(l, mid, nums), subFunc(mid + 1, r, nums));
    }
}
