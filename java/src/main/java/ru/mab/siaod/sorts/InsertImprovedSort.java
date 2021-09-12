package ru.mab.siaod.sorts;

/**
 * Улучшенный вариант сортировки вставками
 * Для поиска места вставки используется двоичный поиск
 * Для сдвига массива используется System.arraycopy
 */
public class InsertImprovedSort extends AbstractSort {
    public static void main(String[] args) {
        new InsertImprovedSort().test(10);
    }

    @Override
    public void sort(int[] array) {
        subSort(array, 0, array.length - 1);
    }

    protected void subSort(int[] array, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int key = array[i];

            int k = i - 1;
            int indexOfGreater = find(key, array, start, i - 1);

            if (k >= indexOfGreater) {
                System.arraycopy(array, indexOfGreater, array, indexOfGreater + 1, k - indexOfGreater + 1);
                array[indexOfGreater] = key;
            }
        }
    }

    /**
     * Метод возвращает индекс первого элемента больше искомого
     */
    int find(int v, int[] arr, int start, int end) {
        if (start >= end) {
            return arr[start] > v ? start : start + 1;
        }
        int mid = (start + end) / 2;
        if (arr[mid] >= v) {
            return find(v, arr, start, mid);
        } else {
            return find(v, arr, mid + 1, end);
        }
    }
}
