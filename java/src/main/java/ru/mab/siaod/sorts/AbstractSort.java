package ru.mab.siaod.sorts;

import java.util.Arrays;

public abstract class AbstractSort implements Sort {

    public void test(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * Math.max(size / 100, 20));
        }

        test(array);
    }

    public void test(int[] array) {
        System.out.println("Unsorted array: " + Arrays.toString(array));

        sort(array);

        System.out.println("Sorted array: " + Arrays.toString(array));
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                throw new RuntimeException("error sorting");
            }
        }
    }

}
