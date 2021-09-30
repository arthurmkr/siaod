package ru.mab.siaod.sorts;

import java.util.Arrays;

public class CountingSort extends AbstractSort {

    public static void main(String[] args) {
        new CountingSort().test(10);
    }

    @Override
    public void sort(int[] array) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int j : array) {
            if (j > max) {
                max = j;
            }
            if (j < min) {
                min = j;
            }
        }

        subSort(array, min, max);
    }

    protected void subSort(int[] array, int min, int max) {
        int[] counts = new int[max - min + 1];
        int[] temp = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            counts[array[i] - min]++;
            temp[i] = array[i];
        }
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            int v = temp[i];
            int countIndex = v - min;
            int count = counts[countIndex];

            array[count - 1] = v;

            counts[countIndex]--;
        }
    }
}
