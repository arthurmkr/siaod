package ru.mab.siaod.sorts;

public class RadixSort extends AbstractSort {
    public static void main(String[] args) {
        new RadixSort().test(10);
    }

    @Override
    public void sort(int[] array) {
        int[] temp = new int[array.length];
        for (int rank = 0; rank < 32; rank++) {
            boolean wasSwap = sortByRank(array, temp, rank);

            if (!wasSwap) {
                break;
            }
        }
    }

    private boolean sortByRank(int[] array, int[] temp, int rank) {
        int[] counts = new int[10];
        int divider = (int) Math.pow(10, rank);

        for (int i = 0; i < array.length; i++) {
            int v = array[i] / divider;
            int countIndex = v % 10;
            counts[countIndex]++;
            temp[i] = array[i];
        }

        if (counts[0] == array.length) {
            return false;
        }

        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            int countIndex = (temp[i] / divider) % 10;

            array[counts[countIndex] - 1] = temp[i];

            counts[countIndex]--;
        }

        return true;
    }
}
