package ru.mab.siaod.math;

/**
 * Алгоритм вычисления "скользящего" среднего за O(1). Расход по памяти O(n), где n - размер "скользящего" окна
 */
public class MovingAverage {
    public final int size;
    int[] arr;
    int curPos = -1;
    int counter = 0;
    int sum = 0;

    public MovingAverage(int size) {
        this.size = size;
        arr = new int[size];
    }

    public double next(int val) {
        curPos = (curPos + 1) % size;

        sum -= arr[curPos];

        arr[curPos] = val;

        sum += val;

        counter = Math.min(counter + 1, size);

        return (double) sum / counter;
    }

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage(3);

//        ma.next(1); => 1
//
//        ma.next(2); => 1.5
//
//        ma.next(3); => 2
//
//        ma.next(4); => 3
    }
}