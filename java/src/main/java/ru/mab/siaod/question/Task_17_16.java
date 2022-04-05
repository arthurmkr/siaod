package ru.mab.siaod.question;

import java.util.ArrayList;
import java.util.List;

public class Task_17_16 {
    public static void main(String[] args) {
//        int[] arr = {30, 15, 60, 75, 45, 15, 15, 45};
//        int[] arr = {75, 105, 120, 75, 90, 135};
        int[] arr = {45, 60, 45, 15};
        System.out.println(findMaxSum(arr));
        List<Integer> result = new ArrayList<>();
        findMaxSumSequence(arr, result);
        System.out.println("res: " + result);
    }

    private static void findMaxSumSequence(int[] arr, List<Integer> result) {
        int[] temps = new int[arr.length];
        int[] sums = new int[arr.length];

        for (int j = arr.length - 1; j >= 0; j--) {

            int v1 = arr[j];
            if (j < arr.length - 2) {
                v1 += sums[j + 2];
            }
            int v2 = 0;
            if (j < arr.length - 1) {
                v2 += sums[j + 1];
            }

            if (v1 >= v2) {
                sums[j] = v1;
                temps[j] = j;
            } else {
                sums[j] = v2;
                temps[j] = j + 1;
            }
        }

        int prev = -1;
        for (int i = 0; i < temps.length; i++) {
            if (temps[i] != prev) {
                result.add(arr[temps[i]]);
                i++;
            }

            prev = temps[i];
        }
    }

    private static int findMaxSum(int[] arr) {
        int[] sums = {0, 0};
        int i = 0;
        for (int j = arr.length - 1; j >= 0; j--) {
            int v1 = arr[j] + sums[i];
            int v2 = sums[(i + 1) % 2];

            sums[i] = Math.max(v1, v2);
            i = (i + 1) % 2;
        }

        return sums[(i + 1) % 2];
    }
}
