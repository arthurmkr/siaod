package ru.mab.siaod.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.mab.siaod.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoArrayMedianTest {
    public static Stream<Arguments> testData() {
        Stream<Arguments> concreteData = Stream.of(
//                Arguments.of(ArrayUtil.generateArray(7, 0, 50), ArrayUtil.generateArray(16, 0, 50)),
                Arguments.of(new int[]{0, 23, 27, 30, 39, 39, 45}, new int[]{3, 4, 12, 13, 13, 17, 20, 21, 23, 23, 24, 25, 27, 29, 33, 41}),
                Arguments.of(new int[]{3, 5, 18, 32, 33, 41, 44}, new int[]{5, 11, 22, 24, 25, 26, 42, 46})
//                Arguments.of(new int[]{8, 9, 11, 31, 32, 34, 37, 42}, new int[]{4, 6, 9, 23, 27, 29, 33, 37}),
//                Arguments.of(new int[]{1, 8, 8, 24, 25, 26, 44, 46}, new int[]{10, 19, 29, 32, 35, 37, 43, 45}),
//                Arguments.of(new int[]{6, 14, 16, 24, 25, 27, 34, 48}, new int[]{8, 12, 24, 25, 29, 38, 38, 41}),
//                Arguments.of(new int[]{4, 5, 12, 14, 14, 17, 33, 44}, new int[]{0, 2, 5, 13, 26, 28, 32, 46}),
//                Arguments.of(new int[]{2, 3, 4, 5, 6, 23, 26, 30}, new int[]{7, 10, 17, 20, 26, 32, 49, 49})
        );

        List<Arguments> generatedData = new ArrayList<>();
        for (int i = 0; i < 0; i++) {
            generatedData.add(Arguments.of(ArrayUtil.generateArray(8, 0, 50), ArrayUtil.generateArray(8, 0, 50)));
        }

        return Stream.concat(concreteData, generatedData.stream());
    }


    @ParameterizedTest
    @MethodSource({"testData"})
    void name(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        System.out.println("arr1: " + Arrays.toString(arr1));
        System.out.println("arr2: " + Arrays.toString(arr2));

        int expectedMedian = TwoArrayMedian.calcExpectedMedian(arr1, arr2);

        int actualMedian = TwoArrayMedian.fastMedian(arr1, arr2);

        assertEquals(expectedMedian, actualMedian);
    }
}