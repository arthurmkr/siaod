package ru.mab.siaod.median;

import static ru.mab.siaod.ArrayUtil.swap;

public class QuickSelect implements StatisticsSelector {
    @Override
    public int find(int[] array, int left, int right, int k) {
        while (true) {
            if (left == right) {
                return array[left];
            }

            int pivotIndex = (right + left) / 2;

            pivotIndex = partition(array, left, right, pivotIndex);


            if (k == pivotIndex) {
                return array[k];
            } else if (k < pivotIndex) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
    }

    int partition(int[] array, int left, int right, int pivotIndex) {
        int pivotValue = array[pivotIndex];
        swap(array, pivotIndex, right);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (array[i] < pivotValue) {
                swap(array, storeIndex, i);
                storeIndex++;
            }
        }

        swap(array, right, storeIndex);
        return storeIndex;
    }
}
