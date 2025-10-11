package ru.mab.siaod.leetcode;

/**
 * 62. Unique Paths
 */
public class UniquePaths {
    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 7) == 28);
        System.out.println(uniquePaths(7, 3) == 28);
        System.out.println(uniquePaths(1, 3) == 1);
        System.out.println(uniquePaths(4, 5) == 35);
        System.out.println(uniquePaths(80, 2) == 80);
        System.out.println(uniquePaths(51, 9) == 1916797311);
    }

    public static int uniquePaths(int m, int n) {
        int minRepeat = Math.min(m, n);
        long max = 0;
        for (int i = 1; i <= n + m - 1; i++) {
            long c = 1;
            for (int k = 1; k <= minRepeat; k++) {
                if (k == minRepeat) {
                    max = c;
                }
                c = (c * (i - k)) / k;
            }
        }

        return (int) max;
    }

    public int uniquePaths2(int m, int n) {
        int dp[] = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }
}
