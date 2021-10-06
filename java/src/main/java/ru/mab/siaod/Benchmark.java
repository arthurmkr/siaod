package ru.mab.siaod;

import java.util.*;

import static ru.mab.siaod.ArrayUtil.generateArray;

public class Benchmark {
    //    public static final int MIN_NUMBER = 0;
//    public static final int MAX_NUMBER = 400000;
    public static final int MIN_NUMBER = Integer.MIN_VALUE / 4;
    public static final int MAX_NUMBER = Integer.MAX_VALUE / 4;
    private static final long MAX_TIME_LIMIT = 5000;
    private static String firstColumnStr;
    private static String otherColumnStr;

    static {
        char[] firstColumn = new char[35];
        Arrays.fill(firstColumn, ' ');
        firstColumnStr = new String(firstColumn);

        char[] otherColumn = new char[10];
        Arrays.fill(otherColumn, ' ');
        otherColumnStr = new String(otherColumn);
    }

    static void printTable(Map<TestingAlgo, List<BenchResult>> results) {
        List<List<Object>> table = new ArrayList<>();

        List<Integer> sizes = new ArrayList<>();
        for (Map.Entry<TestingAlgo, List<BenchResult>> entry : results.entrySet()) {
            List<Object> columns = new ArrayList<>();
            columns.add(entry.getKey().getName());

            int sizeIndex = 0;
            for (BenchResult result : entry.getValue()) {
                columns.add(result.taken());

                if (sizeIndex >= sizes.size()) {
                    sizes.add(result.size);
                }
                sizeIndex++;
            }

            table.add(columns);
        }

        StringBuilder builder = new StringBuilder(normalizeFirstColumn("Sort \\ Size", firstColumnStr));
        for (Integer size : sizes) {
            builder.append(normalizeFirstColumn(String.valueOf(size), otherColumnStr));
        }

        System.out.println(builder + "\n");
        for (List<Object> row : table) {
            StringBuilder rowBuilder = new StringBuilder(normalizeFirstColumn(row.get(0).toString(), firstColumnStr));

            for (int i = 1; i < row.size(); i++) {
                rowBuilder.append(normalizeFirstColumn(String.valueOf(row.get(i)), otherColumnStr));
            }

            System.out.println(rowBuilder);
        }
    }

    private static StringBuilder normalizeFirstColumn(String columnValue, String columnTemplate) {
        StringBuilder builder = new StringBuilder(columnTemplate);

        builder.replace(0, columnValue.length(), columnValue);
        return builder;
    }

    static void printExtendedStatistics(Map<TestingAlgo, List<BenchResult>> results) {
        for (Map.Entry<TestingAlgo, List<BenchResult>> entry : results.entrySet()) {
            System.out.println("Sort by " + entry.getKey().getName());

            BenchResult prev = null;
            for (BenchResult res : entry.getValue()) {
                System.out.println("size: " + res.size + ",\t taken: " + res.taken() + ", \ttime scale: " + res.scale(prev));

                prev = res;
            }
        }
    }

    private static void checkOrder(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] - array[i] < 0) {
                throw new RuntimeException(Arrays.toString(array));
            }
        }
    }

    public void runTest(List<TestingAlgo> consumers, int initSize, int iterationCount) {
        Map<TestingAlgo, List<BenchResult>> results = run(consumers, initSize, iterationCount);

        printTable(results);
    }

    private Map<TestingAlgo, List<BenchResult>> run(List<TestingAlgo> algos, int initSize, int iterationCount) {
        for (TestingAlgo algo : algos) {
            warmingUp(algo);
        }

        Map<TestingAlgo, List<BenchResult>> results = new HashMap<>();

        int size = initSize;
        Map<TestingAlgo, Boolean> skips = new HashMap<>();
        for (int iterationIndex = 0; iterationIndex < iterationCount; iterationIndex++) {
            int[] array = generateArray(size, MIN_NUMBER, MAX_NUMBER);

            System.out.println("Sort array: " + size);

            for (TestingAlgo algo : algos) {
                if (skips.containsKey(algo)) {
                    continue;
                }

                BenchResult sortResult = new BenchResult(size);

                algo.run(Arrays.copyOf(array, array.length));

                sortResult.finish();

                results.computeIfAbsent(algo, key -> new ArrayList<>())
                        .add(sortResult);


                if (sortResult.taken() > MAX_TIME_LIMIT) {
                    skips.put(algo, true);
                }
            }

            size *= 2;
        }

        return results;
    }

    void warmingUp(TestingAlgo algo) {
        int[] array = generateArray(100000);
        algo.run(array);
    }

    static class BenchResult {
        int size;
        long start = System.currentTimeMillis();
        long end;

        public BenchResult(int size) {
            this.size = size;
        }

        void finish() {
            end = System.currentTimeMillis();
        }

        long taken() {
            return end - start;
        }

        double scale(BenchResult prev) {
            return prev == null ? 0 : (double) taken() / prev.taken();
        }
    }

}
