package ru.mab.siaod.median;

import ru.mab.siaod.Benchmark;
import ru.mab.siaod.TestingAlgo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MedianTest {
    public static void main(String[] args) {
        List<TestingAlgo> algos = Stream.of(
                        new SortingSelector(),
                        new QuickSelect())
                .map(MedianTest::createAlgo)
                .collect(Collectors.toList());

        new Benchmark().runTest(algos, 10000, 10);
    }

    private static TestingAlgo createAlgo(StatisticsSelector algo) {
        return new TestingAlgo() {
            @Override
            public String getName() {
                return algo.getClass().getSimpleName();
            }

            @Override
            public void run(int[] arr) {
                algo.median(arr);
            }
        };
    }
}
