package ru.mab.siaod.sorts;

public class MergeImprovedSort extends MergeSort {
    /**
     * Размера массива при котором происходит вызов сортировки вставками
     */
    private static final int MAX_SIZE = 50;
    private final InsertImprovedSort insertSort = new InsertImprovedSort();

    public static void main(String[] args) {
        new MergeImprovedSort().test(100);
    }

    @Override
    public void sort(int[] array) {
        subSort(array, 0, array.length - 1, MAX_SIZE);
    }

    private void subSort(int[] array, int start, int end, int maxSize) {
        if (start >= end) {
            return;
        }

        if ((end - start) <= maxSize) {
            insertSort.subSort(array, start, end);
        } else {
            int middle = (end + start) / 2;
            subSort(array, start, middle, maxSize);
            subSort(array, middle + 1, end, maxSize);

            merge(array, start, middle, end);
        }
    }
}
