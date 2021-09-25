package ru.mab.siaod.median;

import ru.mab.siaod.ArrayUtil;

import java.util.Arrays;
import java.util.List;

import static ru.mab.siaod.ArrayUtil.swap;

public class MedianTest {
    static int GROUP_COUNT = 256;
//    static long MAX = (long) Math.pow(GROUP_COUNT, 3) / 2;
    static long MAX = (long) Math.pow(GROUP_COUNT, 2) / 2;

    public static void main(String[] args) {
        long[] array = ArrayUtil.generateArray(10000000, 1, MAX / 2 - 1
        );

//        long[] array = {14, 3, 4, 9, 2, 4, 2, 15, 18, 16};
//        System.out.println(Arrays.toString(array));
        List<Selector> algos = List.of(
                new SortedSelect(),
                new Quickselect(),
                new CounterSelect()
        );

        for (Selector selector : algos) {
            long[] temp = Arrays.copyOf(array, array.length);

            long time = System.currentTimeMillis();
            long result = selector.find(temp);
            time = System.currentTimeMillis() - time;

            System.out.println(selector.getClass().getSimpleName() + ": " + result + ", taken: " + time);
        }

//        Arrays.sort(array);
//        System.out.println(Arrays.toString(array));
    }

    interface Selector {
        long find(long[] array);
    }

    static class SortedSelect implements Selector {
        @Override
        public long find(long[] array) {
            Arrays.sort(array);
            return array[array.length / 2];
        }
    }

    static class CounterSelect implements Selector {


        @Override
        public long find(long[] array) {
            long intervalSize = MAX / (GROUP_COUNT / 2); //Integer.MAX_VALUE / (GROUP_COUNT / 2);

            return sub(array, -MAX, MAX, array.length / 2, intervalSize) - MAX;
        }

        private long sub(long[] array, long minLimit, long maxLimit, long k, long intervalSize) {
//            System.out.println("interval: " + intervalSize);
            long[] counts = calcCounts(array, maxLimit, minLimit, intervalSize);

            long sum = 0;
            for (int i = 0; i < counts.length; i++) {
                if (sum + counts[i] > k) {
                    if (intervalSize == 1) {
                        return i;
                    } else {
                        long newMinLimit = (i) * intervalSize + minLimit;
                        long newMaxLimit = (i + 1) * intervalSize + minLimit;
                        long sub = sub(array, newMinLimit, newMaxLimit, k - sum, intervalSize / GROUP_COUNT);
                        return i * intervalSize + sub;
                    }
                }
                sum += counts[i];
            }
            return -1;
        }
        long[] counts = new long[GROUP_COUNT];
        private long[] calcCounts(long[] array, long maxLimit, long minLimit, long intervalSize) {
            for(int i = 0; i < counts.length; i++) {
                counts[i] = 0;
            }

            for (long j : array) {
                if (j < minLimit || j >= maxLimit) {
                    continue;
                }

                int index = (int) ((j - minLimit) / intervalSize);

                counts[index]++;
            }

            return counts;
        }
    }

    static class Quickselect implements Selector {
        @Override
        public long find(long[] array) {
            return select(array, 0, array.length - 1, array.length / 2);
        }

        long select(long[] array, int left, int right, int k) {
            while (true) {
                if (left == right) {
                    return array[left];
                }

                int pivotIndex = (right + left) / 2;

                pivotIndex = partition(array, left, right, pivotIndex);


                if (k == pivotIndex) {
                    return array[k];
                } else if (k < pivotIndex) {
                    right = pivotIndex - 1;
                } else {
                    left = pivotIndex + 1;
                }
            }
        }

        int partition(long[] array, int left, int right, int pivotIndex) {
//            System.out.println("pivotIndex: " + pivotIndex);
            long pivotValue = array[pivotIndex];
            swap(array, pivotIndex, right);

            int storeIndex = left;
            for (int i = left; i < right; i++) {
                if (array[i] < pivotValue) {
                    swap(array, storeIndex, i);
                    storeIndex++;
                }
            }

            swap(array, right, storeIndex);
            return storeIndex;
        }
    }
}
