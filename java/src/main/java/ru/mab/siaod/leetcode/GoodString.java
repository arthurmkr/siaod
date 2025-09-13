package ru.mab.siaod.leetcode;

/**
 * https://leetcode.com/problems/count-good-numbers
 */
public class GoodString {
    public int countGoodNumbers(long n) {
        long mod = (long) (1e9 + 7);

        return (int) ((pow(20, n / 2, mod) * (n % 2 == 0 ? 1 : 5)) % mod);
    }

    long pow(long x, long y, long mod) {
        if(y == 0)  {
            return 1;
        }

        long z = 1;
        for (long n = y; n > 1; n /= 2) {
            if (n % 2 == 1) {
                z = (z * x) % mod;
            }
            x = (x * x) % mod;
        }

        return (z * x) % mod;
    }
}
