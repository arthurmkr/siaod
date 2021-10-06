package ru.mab.siaod.median;

import java.util.Arrays;

public class SortingSelector implements StatisticsSelector {
    @Override
    public int find(int[] arr, int left, int right, int k) {
        Arrays.sort(arr, left, right);

        return arr[k];
    }
}
