package ru.mab.siaod.sorts;

import ru.mab.siaod.Benchmark;
import ru.mab.siaod.TestingAlgo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortTest {
    public static void main(String[] args) {
        List<TestingAlgo> algos = Stream.of(
                        new JdkSort()
//                new HeapSort(),
//                new ColumnSort(),
//                new MergeSort(),
//                new MergeImprovedSort(),
//                new QuickSort(),
//                new RandomizedQuickSort(),
//                new HoarePartitionQuickSort(),
//                new RandomHoarePartitionQuickSort(),
//                new QuickSortWithInsert()
                        // TODO работает только на положительных числах и небольшом диапазоне
//                new CountingSort()
                        // TODO работает только на положительных числах
//                new ImprovedCountingSort(),
                        // TODO работает только на положительных числах
//                new RadixSort()

//                new SelectSort(),
//                new BubbleImprovedSort(),
//                new InsertSort(),
//                new InsertImprovedSort()
                )
                .map(SortTest::createAlgo)
                .collect(Collectors.toList());

        new Benchmark().runTest(algos, 10000, 7);
    }

    private static TestingAlgo createAlgo(Sort s) {
        return new TestingAlgo() {
            @Override
            public String getName() {
                return s.getClass().getSimpleName();
            }

            @Override
            public void run(int[] arr) {
                s.sort(arr);
            }
        };
    }
}
