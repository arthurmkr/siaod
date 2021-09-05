package ru.mab.siaod.sorts;

import java.util.Arrays;

public class BubbleImprovedSort extends AbstractSort {
    public static void main(String[] args) {
        int[] ints = {5, 4, 1, 2, 3};
        new BubbleImprovedSort().sort(ints);

        System.out.println(Arrays.toString(ints));
    }

    @Override
    public void sort(int[] array) {
        boolean swapHappened = true;
        int lastSwapIndex = 0;
        int border = array.length - 1;

        while (swapHappened) {
            swapHappened = false;

            for (int i = 0; i < border; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    swapHappened = true;
                    lastSwapIndex = i;
                }
            }

            border = lastSwapIndex;
        }
    }
}
