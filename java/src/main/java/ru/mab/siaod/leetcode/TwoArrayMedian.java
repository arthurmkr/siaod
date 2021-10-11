package ru.mab.siaod.leetcode;

import java.util.Arrays;

public class TwoArrayMedian {
    public static int fastMedian(int[] arr1, int[] arr2) {
        return subFastMedian(arr1, 0, arr1.length, arr2, 0, arr2.length);
    }

    private static int subFastMedian(int[] arr1, int start1, int end1, int[] arr2, int start2, int end2) {

        if (start1 == end1) {
            if ((arr1.length + arr2.length) % 2 == 1) {
                return arr1[end1-1];
            }

            int diff1 = Math.abs(arr1[end1] - arr1[end1 - 1]);
            int diff2 = Math.abs(arr1[end1] - arr2[end2 - 1]);
            if (diff1 < diff2) {
                return (arr1[end1] + arr1[end1 - 1]) / 2;
            } else {
                return (arr1[end1] + arr2[end2 - 1]) / 2;
            }
        }

        if (start2 == end2) {
            if ((arr1.length + arr2.length) % 2 == 1) {
                return arr2[end2-1];
            }
            int diff1 = Math.abs(arr2[end2] - arr2[end2 - 1]);
            int diff2 = Math.abs(arr2[end2] - arr1[end1 - 1]);
            if (diff1 < diff2) {
                return (arr2[end2] + arr2[end2 - 1]) / 2;
            } else {
                return (arr2[end2] + arr1[end1 - 1]) / 2;
            }
        }

        int size1 = end1 - start1;
        int size2 = end2 - start2;
        int fullSize = size1 + size2;

        int fullHalf = fullSize / 2;
        int minMid = Math.min(size1 / 2, size2 / 2);
        int maxMid = fullHalf - minMid;

        int mid1;
        int mid2;
        if (size1 > size2) {
            mid1 = start1 + maxMid;
            mid2 = start2 + minMid;
        } else if (size1 < size2) {
            mid1 = start1 + minMid;
            mid2 = start2 + maxMid;
        } else {
            mid1 = start1 + minMid;
            mid2 = start2 + minMid;
        }

        System.out.println("R: " + fullHalf + ", minMid: " + minMid + ", maxMid: " + maxMid);
        System.out.println("arr1[" + mid1 + "]: " + arr1[mid1] + ", arr2[" + mid2 + "]: " + arr2[mid2]);

        if (arr1[mid1] < arr2[mid2]) {
            return subFastMedian(arr1, mid1, end1, arr2, start2, mid2);
        } else {
            return subFastMedian(arr1, start1, mid1, arr2, mid2, end2);
        }
    }

    public static int calcExpectedMedian(int[] arr1, int[] arr2) {
        int[] fullArr = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, fullArr, 0, arr1.length);
        System.arraycopy(arr2, 0, fullArr, arr1.length, arr2.length);

        Arrays.sort(fullArr);
        System.out.println("Full arr: " + Arrays.toString(fullArr));
        if (fullArr.length % 2 == 0) {
            int lowerMedian = fullArr[fullArr.length / 2];
            int upperMedian = fullArr[fullArr.length / 2 - 1];
            return (lowerMedian + upperMedian) / 2;
        } else {
            return fullArr[fullArr.length / 2];
        }
    }
}
