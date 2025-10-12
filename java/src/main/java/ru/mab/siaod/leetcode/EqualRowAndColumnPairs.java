package ru.mab.siaod.leetcode;

import java.util.*;

/**
 * 2352. Equal Row and Column Pairs
 */
public class EqualRowAndColumnPairs {
    public static void main(String[] args) {
        System.out.println(equalPairs(new int[][]{{3, 2, 1}, {1, 7, 6}, {2, 7, 7}}) == 1);
        System.out.println(equalPairs(new int[][]{{3, 1, 2, 2}, {1, 4, 4, 5}, {2, 4, 2, 2}, {2, 4, 2, 2}}) == 3);
    }

    public static int equalPairs(int[][] grid) {
        Map<Integer, List<Integer>> hashToCollisions = new HashMap<>();
        int max = grid.length;
        for (int i = 0; i < max; i++) {
            hashToCollisions.computeIfAbsent(Arrays.hashCode(grid[i]), h -> new ArrayList<>(1))
                    .add(i);
        }

        int[] buf = new int[max];
        int cnt = 0;
        for (int columnIndex = 0; columnIndex < max; columnIndex++) {
            for (int i = 0; i < max; i++) {
                buf[i] = grid[i][columnIndex];
            }
            for (Integer row : hashToCollisions.getOrDefault(Arrays.hashCode(buf), List.of())) {
                if (Arrays.equals(grid[row], buf)) {
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
