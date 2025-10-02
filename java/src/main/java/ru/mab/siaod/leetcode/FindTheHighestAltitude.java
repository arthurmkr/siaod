package ru.mab.siaod.leetcode;

public class FindTheHighestAltitude {
    public static void main(String[] args) {
        System.out.println(largestAltitude(new int[]{-5, 1, 5, 0, -7}) == 1);
        System.out.println(largestAltitude(new int[]{-4, -3, -2, -1, 4, 3, 2}) == 0);
    }

    public static int largestAltitude(int[] gain) {
        int max = 0;
        int sum = 0;
        for(int i = 0; i < gain.length; i++) {
            sum += gain[i];
            max = Math.max(max, sum);
        }

        return max;
    }
}
