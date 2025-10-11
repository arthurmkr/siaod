package ru.mab.siaod.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 215. Kth Largest Element in an Array
 */
public class KthLargestElementInArray {
    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2) == 5);
        System.out.println(findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4) == 4);
    }

    public static int findKthLargest(int[] nums, int k) {
        Queue<Integer> heap = new PriorityQueue<>();
        for (int v : nums) {
            heap.add(-v);
        }

        int v = 0;
        while (k > 0) {
            v = heap.poll();
            k--;
        }

        return -v;
    }
}
