package ru.mab.siaod.leetcode;

/**
 * 231. Power of Two
 */
public class PowerOfTwo {
    public static void main(String[] args) {
        PowerOfTwo alg = new PowerOfTwo();
        System.out.println(alg.isPowerOfTwo(1) == true);
        System.out.println(alg.isPowerOfTwo(16) == true);
        System.out.println(alg.isPowerOfTwo(3) == false);
        System.out.println(alg.isPowerOfTwo(5) == false);
        System.out.println(alg.isPowerOfTwo(1 << 30) == true);
    }

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
