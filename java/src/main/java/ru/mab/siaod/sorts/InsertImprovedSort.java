package ru.mab.siaod.sorts;

public class InsertImprovedSort extends AbstractSort {
    public static void main(String[] args) {
        new InsertImprovedSort().test(10);
    }

    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];

            int j = i - 1;
            int k = j;
            while (j >= 0 && array[j] > key) {
                j--;
            }

            System.arraycopy(array, j + 1, array, j + 2, k - j);

            array[j + 1] = key;
        }
    }
}
