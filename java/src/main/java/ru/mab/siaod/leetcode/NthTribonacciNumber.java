package ru.mab.siaod.leetcode;

/**
 * 1137. N-th Tribonacci Number
 */
public class NthTribonacciNumber {
    public static void main(String[] args) {
        System.out.println(tribonacci(0) == 0);
        System.out.println(tribonacci(1) == 1);
        System.out.println(tribonacci(2) == 1);
        System.out.println(tribonacci(3) == 2);
        System.out.println(tribonacci(4) == 4);
        System.out.println(tribonacci(25) == 1389537);
    }

    public static int tribonacci(int n) {
        int a = 0;
        int b = 1;
        int c = 1;

        for(int i = 0; i < n; i++) {
            int tmp = a + b + c;
            a = b;
            b = c;
            c = tmp;
        }

        return a;
    }
}
