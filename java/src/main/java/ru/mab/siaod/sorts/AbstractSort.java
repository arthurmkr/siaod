package ru.mab.siaod.sorts;

public abstract class AbstractSort implements Sort {
    protected void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

}
