package ru.mab.siaod.sorts;

public class ImprovedCountingSort extends CountingSort {

    public static void main(String[] args) {
        new ImprovedCountingSort().test(10);
    }

    protected void subSort(int[] array, int min, int max) {
        int[] counts = new int[max - min + 1];

        for (int value : array) {
            counts[value - min]++;
        }

        int k = 0;
        for(int i = 0; i < counts.length; i++) {
            for(int j = 0; j < counts[i]; j++) {
                array[k] = i;
                k++;
            }
        }
    }
}
