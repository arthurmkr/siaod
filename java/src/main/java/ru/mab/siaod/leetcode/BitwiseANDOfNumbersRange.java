package ru.mab.siaod.leetcode;

/**
 * 201. Bitwise AND of Numbers Range
 */
public class BitwiseANDOfNumbersRange {
    public static void main(String[] args) {
        BitwiseANDOfNumbersRange alg = new BitwiseANDOfNumbersRange();

//        System.out.println(Integer.toBinaryString(-1 << 30));
//        System.out.println(Integer.toBinaryString((-1 << 30) >> 2));
//        System.out.println(Integer.toBinaryString((-1 << 30) >>> 2));
        System.out.println(alg.rangeBitwiseAnd(5, 7) == 4);
        System.out.println(alg.rangeBitwiseAnd(1, 2147483647) == 0);
        System.out.println(alg.rangeBitwiseAnd(0, 1) == 0);
        System.out.println(alg.rangeBitwiseAnd(2, 3) == 2);
    }

    public int rangeBitwiseAnd(int left, int right) {
        if(left == right) {
            return left;
        }
        int tmp = right ^ left;
        int m = 1 << 30;
        int clean = Integer.MIN_VALUE;
        for (int i = 1; i < 31; i++) {
            if ((m & tmp) != 0) {
                break;
            }

            m >>= 1;
            clean >>= 1;
        }

        return right & clean;
    }

//    public int rangeBitwiseAnd(int left, int right) {
//        int res = right;
//        int mask = 1 << 30;
//        int cleanMask = -1 << 31;
//
//        if(left == right) {
//            return left;
//        }
//
//        for (int i = 30; i > 0; i--) {
//            if ((right & mask) == (left & mask)) {
//                cleanMask >>= 1;
//                mask >>= 1;
//            } else {
//                break;
//            }
//        }
//        return res&cleanMask;
//    }
}
