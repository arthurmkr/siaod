package ru.mab.siaod.sorts;

import java.util.Arrays;

public abstract class AbstractSort implements Sort {
    protected void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    public void test(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * Math.max(size / 100, 20));
        }

//        array = new int[]{1, 1, 2, 4,3,2,1,2,3};
//        System.out.println("Unsorted array: " + Arrays.toString(array));

        sort(array);

//        System.out.println("Sorted array: " + Arrays.toString(array));
    }

}
