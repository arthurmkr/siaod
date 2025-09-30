package ru.mab.siaod.leetcode;

/**
 * 7. Reverse Integer
 */
public class ReverseInteger {
    public static void main(String[] args) {
        ReverseInteger alg = new ReverseInteger();

        System.out.println(alg.reverse(123) == 321);
        System.out.println(alg.reverse(-123) == -321);
        System.out.println(alg.reverse(-923) == -329);
        System.out.println(alg.reverse(1534236469) == 0);
        System.out.println(alg.reverse(-2147483648));
    }

    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res *= 10;
            res += x % 10;
            if(res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
                return 0;
            }
            x /= 10;
        }

        return (int) res;
    }
}
