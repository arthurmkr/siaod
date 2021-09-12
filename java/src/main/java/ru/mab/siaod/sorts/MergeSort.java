package ru.mab.siaod.sorts;

public class MergeSort extends AbstractSort {

    public static void main(String[] args) {
        new MergeSort().test(10);
    }

    @Override
    public void sort(int[] array) {
        subSort(array, 0, array.length - 1);
    }

    private void subSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        int middle = (end + start) / 2;
        subSort(array, start, middle);
        subSort(array, middle + 1, end);

        merge(array, start, middle, end);
    }

    protected void merge(int[] array, int start, int middle, int end) {
        // + 1 элемент массива для "сигнального" значения
        int[] leftArr = new int[middle - start + 1 + 1];
        int[] rightArr = new int[end - middle + 1];

        System.arraycopy(array, start, leftArr, 0, leftArr.length - 1);
        System.arraycopy(array, middle + 1, rightArr, 0, rightArr.length - 1);

        leftArr[leftArr.length - 1] = Integer.MAX_VALUE;
        rightArr[rightArr.length - 1] = Integer.MAX_VALUE;

        for (int k = start, i = 0, j = 0; k <= end; k++) {
            if (leftArr[i] <= rightArr[j]) {
                array[k] = leftArr[i];
                i++;
            } else {
                array[k] = rightArr[j];
                j++;
            }
        }
    }
}
