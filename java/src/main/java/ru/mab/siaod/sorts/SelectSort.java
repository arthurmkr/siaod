package ru.mab.siaod.sorts;

import static ru.mab.siaod.ArrayUtil.swap;

public class SelectSort extends AbstractSort {
    public static void main(String[] args) {
        new SelectSort().test(10);
    }
    @Override
    public void sort(int[] array) {
        int minIndex;
        for (int i = 0; i < array.length - 1; i++) {
            minIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }

            swap(array, i, minIndex);
        }
    }
}
