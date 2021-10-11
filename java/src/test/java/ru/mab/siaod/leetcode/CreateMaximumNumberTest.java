package ru.mab.siaod.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class CreateMaximumNumberTest {

    public static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(5, new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, new int[]{9, 8, 6, 5, 3}),
                Arguments.of(5, new int[]{6, 7}, new int[]{6, 0, 4}, new int[]{6, 7, 6, 0, 4}),
                Arguments.of(3, new int[]{3, 9}, new int[]{8, 9}, new int[]{9, 8, 9})
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void test(int k, int[] arr1, int[] arr2, int[] expected) {
        int[] actual = CreateMaximumNumber.eval(k, arr1, arr2);

        assertArrayEquals(expected, actual);
    }
}