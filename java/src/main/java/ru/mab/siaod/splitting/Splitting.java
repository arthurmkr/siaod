package ru.mab.siaod.splitting;

import java.util.Arrays;

public class Splitting {
    public static void main(String[] args) {
        int prices[][] = {{1, 1}, {2, 5}, {3, 8}, {4, 9}, {5, 10}, {6, 17}, {7, 17}, {8, 20}, {9, 24}, {10, 30}};

        Splitter splitterSimple = new SplitterSimple();
//        for (int i = 0; i <= 10; i++) {
            System.out.println(splitterSimple.split(10, prices));
//        }
    }

    interface Splitter {
        int split(int length, int[][] prices);
    }

    public static class SplitterSimple implements Splitter {
        @Override
        public int split(int length, int[][] prices) {
            if (length <= 0) {
                return 0;
            }

            int max = -1;
            for (int i = 1; i <= length; i++) {
                max = Math.max(max, prices[i - 1][1] + split(length - prices[i - 1][0], prices));
            }

            System.out.println("n: " + length + ", r: " + max);
            return max;
        }
    }

    public static class SplitterMemo implements Splitter {
        @Override
        public int split(int length, int[][] prices) {
            int[] memo = new int[length];
            Arrays.fill(memo, -1);

            return splitRecursive(length, prices, memo);
        }

        private int splitRecursive(int length, int[][] prices, int[] memo) {
            if(length <= 0) {
                return 0;
            }

            if (memo[length-1] > 0) {
                return memo[length-1];
            }

            int max = -1;
            for (int i = 1; i <= length; i++) {
                max = Math.max(max, prices[i - 1][1] + splitRecursive(length - prices[i - 1][0], prices, memo));
            }

            memo[length-1] = max;
            System.out.println("n: " + length + ", r: " + max);
            return max;

        }
    }
}
