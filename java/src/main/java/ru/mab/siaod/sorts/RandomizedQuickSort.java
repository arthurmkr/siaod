package ru.mab.siaod.sorts;

import java.util.Random;

import static ru.mab.siaod.ArrayUtil.swap;

public class RandomizedQuickSort extends QuickSort {
    private Random random = new Random();

    public static void main(String[] args) {
        new RandomizedQuickSort().test(10);
    }

    @Override
    protected int partition(int[] array, int l, int r) {
        int randomPivotIndex = random.nextInt(r - l) + l;
        swap(array, randomPivotIndex, r);
        return super.partition(array, l, r);
    }
}
