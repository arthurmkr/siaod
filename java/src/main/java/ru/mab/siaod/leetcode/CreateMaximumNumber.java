package ru.mab.siaod.leetcode;

import java.util.Arrays;

public class CreateMaximumNumber {
    public static int[] eval(int k, int[] arr1, int[] arr2) {
        int[] result = new int[k];

        return func(result, 0, arr1, 0, arr2, 0);
    }

    private static int[] func(int[] result, int resultCurIndex, int[] arr1, int index1, int[] arr2, int index2) {
        if (resultCurIndex == result.length || (index1 >= arr1.length && index2 >= arr2.length)) {
            return Arrays.copyOf(result, result.length);
        }

        if (index1 >= arr1.length) {
            for(; index2 < arr2.length && resultCurIndex < result.length; index2++, resultCurIndex++) {
                result[resultCurIndex] = arr2[index2];
            }
            return Arrays.copyOf(result, result.length);
        } else if (index2 >= arr2.length) {
            for(; index1 < arr1.length && resultCurIndex < result.length; index1++, resultCurIndex++) {
                result[resultCurIndex] = arr1[index1];
            }
            return Arrays.copyOf(result, result.length);
        }


        result[resultCurIndex] = arr1[index1];
        int[] res1 = func(result, resultCurIndex + 1, arr1, index1 + 1, arr2, index2);

        result[resultCurIndex] = arr1[index1];
        int[] res2 = func(result, resultCurIndex + 1, arr1, index1, arr2, index2 + 1);

        for (int i = 0; i < res1.length; i++) {
            if (res1[i] > res2[i]) {
                return res1;
            } else if (res2[i] > res1[i]) {
                return res2;
            }
        }

        return res1;
    }
}
