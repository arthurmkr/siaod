package ru.mab.siaod.sorts;

import java.util.*;

import static ru.mab.siaod.ArrayUtil.generateArray;

public class SortTest {
    private static final long MAX_TIME_LIMIT = 5000;
    private static String firstColumnStr;
    private static String otherColumnStr;

    static {
        char[] firstColumn = new char[25];
        Arrays.fill(firstColumn, ' ');
        firstColumnStr = new String(firstColumn);

        char[] otherColumn = new char[10];
        Arrays.fill(otherColumn, ' ');
        otherColumnStr = new String(otherColumn);
    }

    public static void main(String[] args) {

        SortTest test = new SortTest();
        int initSize = 10000;
        int iterationCount = 10;
        List<Sort> sorts = Arrays.asList(
                new JdkSort(),
//                new HeapSort(),
//                new MergeSort(),
//                new MergeImprovedSort(),
                new QuickSort(),
//                new RandomizedQuickSort(),
//                new HoarePartitionQuickSort(),
//                new RandomHoarePartitionQuickSort(),
                new QuickSortWithInsert()

//                new SelectSort(),
//                new BubbleImprovedSort(),
//                new InsertSort(),
//                new InsertImprovedSort()
        );

        Map<Sort, List<SortResult>> results = test.run(sorts, initSize, iterationCount);

//        printExtendedStatistics(results);
        printTable(results);
    }

    static void printTable(Map<Sort, List<SortResult>> results) {
        List<List<Object>> table = new ArrayList<>();

        List<Integer> sizes = new ArrayList<>();
        for (Map.Entry<Sort, List<SortResult>> entry : results.entrySet()) {
            List<Object> columns = new ArrayList<>();
            columns.add(entry.getKey().getClass().getSimpleName());

            int sizeIndex = 0;
            for (SortResult result : entry.getValue()) {
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

    static void printExtendedStatistics(Map<Sort, List<SortResult>> results) {
        for (Map.Entry<Sort, List<SortResult>> entry : results.entrySet()) {
            System.out.println("Sort by " + entry.getKey().getClass().getSimpleName());

            SortResult prev = null;
            for (SortResult res : entry.getValue()) {
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

    private Map<Sort, List<SortResult>> run(List<Sort> algos, int initSize, int iterationCount) {
        for (Sort algo : algos) {
            warmingUp(algo);
        }

        Map<Sort, List<SortResult>> results = new HashMap<>();

        int size = initSize;
        Map<Sort, Boolean> skips = new HashMap<>();
        for (int iterationIndex = 0; iterationIndex < iterationCount; iterationIndex++) {
            int[] array = generateArray(size, Integer.MIN_VALUE / 4, Integer.MAX_VALUE / 4);

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

        return results;
    }

    void warmingUp(Sort algo) {
        int[] array = generateArray(100000);
        algo.sort(array);

        checkOrder(array);
    }

    static class SortResult {
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
