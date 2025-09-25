package ru.mab.siaod.leetcode;

public class ClimbingStairs {
    public static void main(String[] args) {
        ClimbingStairs alg = new ClimbingStairs();
        for (int i = 1; i <= 10; i++) {
            System.out.println(alg.climbStairs(i));

        }
    }

    public int climbStairs(int n) {
        int a = 0, b = 1, i = 0;
        do {
            int c = a + b;
            a = b;
            b = c;
            i++;
        } while (i <= n);

        return a;
    }

//
//    public int climbStairs(int n) {
//        if (n < 0) {
//            return 0;
//        } else if (n == 0) {
//            return 1;
//        }
//
//        return climbStairs(n - 1) + climbStairs(n - 2);
//    }

//
//    public int climbStairs(int n) {
//        int[] counts = new int[n + 1];
//        counts[0] = 1;
//        counts[1] = 1;
//
//        return subClimbStairs(n, counts);
//    }
//
//    private int subClimbStairs(int n, int[] counts) {
//        if (n < 0) {
//            return 0;
//        }
//
//        if (counts[n] == 0) {
//            counts[n] = subClimbStairs(n - 1, counts) + subClimbStairs(n - 2, counts);
//        }
//
//        return counts[n];
//    }
}
