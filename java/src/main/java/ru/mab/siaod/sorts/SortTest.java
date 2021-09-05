package ru.mab.siaod.sorts;

import java.util.*;

public class SortTest {
    private static final long MAX_TIME_LIMIT = 5000;
    Random random = new Random();

    public static void main(String[] args) {

        SortTest test = new SortTest();
        int initSize = 10000;
        int iterationCount = 8;
        List<Sort> sorts = Arrays.asList(
                new Sort() {
                    @Override
                    public void sort(int[] array) {
                        Arrays.sort(array);
                    }
                },
                new HeapSort(),
                new BubbleSort(),
                new BubbleImprovedSort()
        );

        test.run(sorts, initSize, iterationCount);
    }

    private static void checkOrder(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] - array[i] < 0) {
                throw new RuntimeException(Arrays.toString(array));
            }
        }
    }

    private void run(List<Sort> algos, int initSize, int iterationCount) {
        for (Sort algo : algos) {
            warmingUp(algo);
        }

        Map<Sort, List<SortResult>> results = new HashMap<>();

        int size = initSize;
        Map<Sort, Boolean> skips = new HashMap<>();
        for (int iterationIndex = 0; iterationIndex < iterationCount; iterationIndex++) {
            int[] array = generateArray(size);

            System.out.println("Sort array: " + size);

            for (Sort algo : algos) {
                if (skips.containsKey(algo)) {
                    continue;
                }

                SortResult sortResult = new SortResult(size);

                algo.sort(Arrays.copyOf(array, array.length));

                sortResult.finish();

                results.computeIfAbsent(algo, key -> new ArrayList<>())
                        .add(sortResult);


                if (sortResult.taken() > MAX_TIME_LIMIT) {
                    skips.put(algo, true);
                }
            }

            size *= 2;
        }


        for (Map.Entry<Sort, List<SortResult>> entry : results.entrySet()) {
            System.out.println("Sort by " + entry.getKey().getClass().getSimpleName());

            SortResult prev = null;
            for (SortResult res : entry.getValue()) {
                System.out.println("size: " + res.size + ", taken: " + res.taken() + ", time scale: " + res.scale(prev));

                prev = res;
            }
        }
    }

    int[] generateArray(int size) {
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(2000);
        }

        return array;
    }

    void warmingUp(Sort algo) {
        int[] array = generateArray(10000);
        algo.sort(array);

        checkOrder(array);
    }

    class SortResult {
        int size;
        long start = System.currentTimeMillis();
        long end;

        public SortResult(int size) {
            this.size = size;
        }

        void finish() {
            end = System.currentTimeMillis();
        }

        long taken() {
            return end - start;
        }

        double scale(SortResult prev) {
            return prev == null ? 0 : (double) taken() / prev.taken();
        }
    }
}
