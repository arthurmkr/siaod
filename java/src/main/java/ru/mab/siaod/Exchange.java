package ru.mab.siaod;

public class Exchange {
    private static int[] coins = {25, 10, 5, 1};
    private static int count =0;

    public static void main(String[] args) {
        int N = 1000000;
        int[][] cache = new int[N+1][coins.length];


        int c = func(N, 0, cache);
        System.out.println(c);
        System.out.println(count);
    }

    private static int func(int n, int index, int[][] cache) {
        if(cache[n][index] != 0) {
            return cache[n][index];
        }
        count++;
        int c = 0;
        if (n == 0) {
            return 1;
        }

        for (int i = index; i < coins.length - 1; i++) {
            if (n < coins[i]) {
                continue;
            }

            c += func(n - coins[i], i, cache);
        }

        int res = c + 1;
        cache[n][index] = res;
        return res;
    }
}
