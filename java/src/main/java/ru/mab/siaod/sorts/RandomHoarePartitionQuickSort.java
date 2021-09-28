package ru.mab.siaod.sorts;

import java.util.Random;

import static ru.mab.siaod.ArrayUtil.swap;

public class RandomHoarePartitionQuickSort extends HoarePartitionQuickSort {
    public static void main(String[] args) {
        new RandomHoarePartitionQuickSort().test(10);
    }
    private Random random = new Random();

    @Override
    protected int partition(int[] array, int l, int r) {
        int randomPivotIndex = random.nextInt(r - l) + l;
        swap(array, randomPivotIndex, r);
        return super.partition(array, l, r);
    }
}
