package ru.mab.siaod.sorts;

import java.util.Arrays;

import static ru.mab.siaod.ArrayUtil.swap;

public class BubbleSort extends AbstractSort {
    public static void main(String[] args) {
        int[] ints = {5, 4, 1, 2, 3};
        new BubbleSort().sort(ints);

        System.out.println(Arrays.toString(ints));
    }

    @Override
    public void sort(int[] array) {
        boolean swapHappened = true;

        while (swapHappened) {
            swapHappened = false;

            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    swapHappened = true;
                }
            }
        }
    }
}
