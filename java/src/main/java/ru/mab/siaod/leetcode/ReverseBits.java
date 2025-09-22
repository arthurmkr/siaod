package ru.mab.siaod.leetcode;

/**
 * 190. Reverse Bits
 */
public class ReverseBits {
    public static void main(String[] args) {
        ReverseBits alg = new ReverseBits();
        System.out.println(alg.reverseBits(43261596) == 964176192);
    }

    public int reverseBits(int n) {
        int res = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            if ((n & mask) != 0) {
                res |= 1;
            }
            mask <<= 1;
        }

        return res;
    }
}
