package ru.mab.siaod;

import java.util.Random;

public class ArrayUtil {
    static Random random = new Random();

    public static int[] generateArray(int size) {
        return generateArray(size, 0, 2000);
    }

    public static int[] generateArray(int size, int min, int max) {
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min) + min;
        }

        return array;
    }
}
