package ru.mab.siaod.sorts;

import static ru.mab.siaod.ArrayUtil.swap;

public class ColumnSort extends AbstractSort {
    public static void main(String[] args) {
        new ColumnSort().test(10);
    }

    @Override
    public void sort(int[] array) {
        int rowCount = (int) Math.ceil(Math.pow(array.length * Math.sqrt(2.), 2. / 3.));
        int columnCount = (int) Math.ceil((double) array.length / rowCount);
//        System.out.println("array size: " + array.length + ", row count: " + rowCount + ", column count: " + columnCount);

        sortByColumns(array, 0, rowCount);

        sortByRows(array, columnCount);

        sortByColumns(array, 0, rowCount);

        sortByColumns(array, -rowCount / 2, rowCount);
    }

    private void sortByRows(int[] array, int rowSize) {
        for (int i = 0; i < array.length / rowSize; i++) {
            insertSort(array, i, array.length, rowSize);
        }
    }

    void sortByColumns(int[] array, int start, int columnSize) {
        for (int curStart = start; curStart < array.length; curStart += columnSize) {
            insertSort(array, curStart, curStart + columnSize, 1);
        }
    }

    void insertSort(int[] array, int start, int end, int step) {
        int realStart = Math.max(0, start);
        int realEnd = Math.min(array.length, end);

        for (int i = realStart; i < realEnd; i += step) {
            int j = i;
            while ((j - step) >= realStart && array[j] < array[j - step]) {
                swap(array, j - step, j);
                j -= step;
            }
        }
    }
}
