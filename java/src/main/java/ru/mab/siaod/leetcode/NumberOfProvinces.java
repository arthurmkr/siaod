package ru.mab.siaod.leetcode;

/**
 * 547. Number of Provinces
 */
public class NumberOfProvinces {
    public static void main(String[] args) {
        System.out.println(findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}) == 2);
        System.out.println(findCircleNum(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}) == 3);
        System.out.println(findCircleNum(new int[][]{{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}}) == 1);
//        System.out.println(findCircleNum(new int[][]{{1}}) == 1);
    }

    public static void dfs(int[][] isConnected, int row, int length) {
        for (int i = 0; i < length; i++) {
            if (isConnected[row][i] != 0 && isConnected[i][i] != 0) {
                isConnected[row][i] = 0;
                dfs(isConnected, i, length);
            }
        }
    }

    public static int findCircleNum(int[][] isConnected) {
        int cnt = 0;
        int length = isConnected.length;
        for (int i = 0; i < length; i++) {
            if (isConnected[i][i] != 0) {
                dfs(isConnected, i, length);
                cnt++;
            }
        }

        return cnt;
    }
}
