package ru.mab.siaod.leetcode;

import java.util.Arrays;

/**
 * 435. Non-overlapping Intervals
 */
public class NonOverlappingIntervals {
    public static void main(String[] args) {
        System.out.println(eraseOverlapIntervals(new int[][]{{1, 3}, {1, 2}, {2, 4}, {2, 3}, {1, 4}, {3, 4},}) == 3);
//        System.out.println(eraseOverlapIntervals(new int[][]{{1, 2}, {1, 2}, {1, 2}}) == 2);
//        System.out.println(eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}}) == 0);
//        System.out.println(eraseOverlapIntervals(new int[][]{{-52, 31}, {-73, -26}, {82, 97}, {-65, -11}, {-62, -49}, {95, 99}, {58, 95}, {-31, 49}, {66, 98}, {-63, 2}, {30, 47}, {-40, -26}}) == 7);
    }

//    public static int eraseOverlapIntervals(int[][] intervals) {
//        Arrays.parallelSort(intervals, (o1, o2) -> {
//            if (o1[1] < o2[1]) {
//                return -1;
//            } else if (o1[1] > o2[1]) {
//                return 1;
//            }
//            return 0;
//        });
//
//        int prevRight = Integer.MIN_VALUE;
//        int count = 0;
//        for (int[] interval : intervals) {
//            if (interval[0] < prevRight) {
//                count++;
//            } else {
//                prevRight = interval[1];
//            }
//        }
//
//        return count;
//    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        int max = intervals[0][1];
        int min = max;

        for(int i = 1; i < intervals.length; i++){
            max = Math.max(max, intervals[i][1]);
            min = Math.min(min, intervals[i][1]);
        }

        int shift = 1 - min;
        int maxIntervalRange = 2 + max - min;
        int[] rightEnds = new int[maxIntervalRange];

        for(int[] interval : intervals){
            int left = interval[0] + shift;
            int right = interval[1] + shift;
            if(left > rightEnds[right]){
                rightEnds[right] = left;
            }
        }

        int start = 1;
        int count = 1;
        for(int i = 2; i < maxIntervalRange; i++){
            if(start <= rightEnds[i]){
                count++;
                start = i;
            }
        }
        return intervals.length - count;
    }
}
