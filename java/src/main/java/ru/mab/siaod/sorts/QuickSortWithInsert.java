package ru.mab.siaod.sorts;

public class QuickSortWithInsert extends QuickSort {
    private final InsertSort insertSort = new InsertSort();
    private int initLowThreshold;
    private int lowThreshold;

    public QuickSortWithInsert() {
    }

    public QuickSortWithInsert(int lowThreshold) {
        this.initLowThreshold = lowThreshold;
    }

    public static void main(String[] args) {
        new QuickSortWithInsert(4).test(20);
    }

    @Override
    public void sort(int[] array) {
        lowThreshold = initLowThreshold <= 0 ? calcLowThreshold(array) : initLowThreshold;
        super.sort(array);

        insertSort.sort(array);
    }

    private int calcLowThreshold(int[] array) {
        return 3 * (int) Math.log(array.length);
    }

    @Override
    protected void quicksort(int[] array, int l, int r) {
        if (r - l > lowThreshold) {
            super.quicksort(array, l, r);
        }
    }
}
