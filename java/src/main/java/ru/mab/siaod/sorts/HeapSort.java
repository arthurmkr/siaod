package ru.mab.siaod.sorts;

public class HeapSort extends AbstractSort {
    @Override
    public void sort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            buildHeap(array, i, array.length);
        }

        for (int i = array.length - 1; i > 0; i--) {
            swap(array, i, 0);
            buildHeap(array, 0, i);
        }
    }

    private void buildHeap(int[] array, int curIndex, int length) {
        int leftIndex = 2 * curIndex + 1;
        int rightIndex = 2 * curIndex + 2;
        int maxIndex = curIndex;

        if (leftIndex < length && array[maxIndex] < array[leftIndex]) {
            maxIndex = leftIndex;
        }

        if (rightIndex < length && array[maxIndex] < array[rightIndex]) {
            maxIndex = rightIndex;
        }

        if (maxIndex != curIndex) {
            swap(array, maxIndex, curIndex);

            buildHeap(array, maxIndex, length);
        }
    }

}