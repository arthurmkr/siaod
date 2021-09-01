package ru.mab.siaod.sorts;

import java.util.Arrays;
import java.util.Random;

public class HeapSort {

    public static void main(String[] args) {
        int N = 1000;
        int[] array = new int[N];
        Random random = new Random();

        for (int i = 0; i < N; i++) {
            array[i] = random.nextInt(2000);
        }

        HeapSort heapSort = new HeapSort();
        heapSort.sort(array);

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] - array[i] < 0) {
                throw new RuntimeException(Arrays.toString(array));
            }
        }
    }

    private void sort(int[] array) {
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

    private void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }
}