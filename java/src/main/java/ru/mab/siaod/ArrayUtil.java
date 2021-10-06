package ru.mab.siaod;

import java.util.Random;

public class ArrayUtil {
    static Random random = new Random();

    public static void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    public static void swap(long[] array, int firstIndex, int secondIndex) {
        long temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    public static int[] generateArray(int size) {
        return generateArray(size, Integer.MIN_VALUE / 4, Integer.MAX_VALUE / 4);
    }

    public static int[] generateArray(int size, int min, int max) {
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
//            array[i] = min +  i;
            array[i] = random.nextInt(max - min) + min;
        }

        return array;
    }

    public static long[] generateArray(int size, long min, long max) {
        long[] array = new long[size];

        for (int i = 0; i < size; i++) {
            array[i] = Math.abs(random.nextLong()) % (max - min) + min;
        }

        return array;
    }
}
