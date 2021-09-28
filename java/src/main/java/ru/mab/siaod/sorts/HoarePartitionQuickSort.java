package ru.mab.siaod.sorts;

import static ru.mab.siaod.ArrayUtil.swap;

public class HoarePartitionQuickSort extends QuickSort {

    public static void main(String[] args) {
        new HoarePartitionQuickSort().test(10);
    }

    @Override
    protected void quicksort(int[] array, int l, int r) {
        if (l < r) {
            int middle = partition(array, l, r);

            quicksort(array, l, middle - 1);
            quicksort(array, middle, r);
        }
    }

    @Override
    protected int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left - 1;
        int j = right + 1;
        while (i < j) {
            do {
                i++;
            } while (array[i] <= pivot && i < right);

            do {
                j--;
            } while (array[j] > pivot && j > left);

            if (i < j) {
                swap(array, i, j);
            }
        }

        return i;
    }
}
