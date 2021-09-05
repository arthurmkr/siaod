package ru.mab.siaod.sorts;

import java.util.Arrays;

public class JdkSort extends AbstractSort {
    @Override
    public void sort(int[] array) {
        Arrays.sort(array);
    }
}
