package ru.mab.siaod.sorts;

import static ru.mab.siaod.ArrayUtil.swap;

public class QuickSort extends AbstractSort {
    public static void main(String[] args) {
        new QuickSort().test(10);
    }

    @Override
    public void sort(int[] array) {
        quicksort(array, 0, array.length - 1);
    }

    protected void quicksort(int[] array, int l, int r) {
        if (r > l) {
            int middle = partition(array, l, r);
            quicksort(array, l, middle - 1);
            quicksort(array, middle + 1, r);
        }
    }

    protected int partition(int[] array, int l, int r) {
        int pivot = array[r];

        int edge = l;
        for (int j = l; j < r; j++) {
            if (array[j] < pivot) {
                swap(array, j, edge);
                edge++;
            }
        }

        swap(array, edge, r);
        return edge;
    }
}
