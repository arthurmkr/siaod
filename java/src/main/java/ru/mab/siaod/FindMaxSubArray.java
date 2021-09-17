package ru.mab.siaod;

import java.util.Arrays;

import static ru.mab.siaod.ArrayUtil.generateArray;

/**
 * Поиск подмассива с максимальной суммой элементов
 */
public class FindMaxSubArray {
    public static void main(String[] args) {
        int[] array = generateArray(10, -1000, 1000);

        System.out.println("Original: " + Arrays.toString(array));

        Finder finder = new Finder();
        printResult(array, finder.find(array), "Max sub array (O(n*lg)): ");

        Finder2 finder2 = new Finder2();
        printResult(array, finder2.find(array), "Max sub array (O(n)): ");

        FinderKadan finderKadan = new FinderKadan();
        printResult(array, finderKadan.find(array), "Max sub array (kadan): ");
    }

    private static void printResult(int[] array, int[] range, String s) {
        int[] maxSubArray = copy(array, range[0], range[1]);
        System.out.println(s + Arrays.toString(maxSubArray));
    }

    static int[] copy(int[] orig, int left, int right) {
        int[] result = new int[right - left + 1];

        System.arraycopy(orig, left, result, 0, result.length);

        return result;
    }

    /**
     * Алгоритм Джея Кадана
     */
    static class FinderKadan {
        private int[] find(int[] array) {
            int ans = array[0],
                    ans_l = 0,
                    ans_r = 0,
                    sum = 0,
                    minus_pos = -1;

            for (int r = 0; r < array.length; r++) {
                sum += array[r];

                if (sum > ans) {
                    ans = sum;
                    ans_l = minus_pos + 1;
                    ans_r = r;
                }

                if (sum < 0) {
                    sum = 0;
                    minus_pos = r;
                }
            }

            return new int[]{ans_l, ans_r, sum};
        }
    }

    /**
     * О(n)
     */
    static class Finder2 {
        private int[] find(int[] array) {
            int max_sum = array[0],
                    ans_l = 0,
                    ans_r = 0,
                    partialSum = 0,
                    min_sum = 0,
                    min_pos = -1;

            for (int r = 0; r < array.length; r++) {
                partialSum += array[r];

                int cur = partialSum - min_sum;
                if (cur > max_sum) {
                    max_sum = cur;
                    ans_l = min_pos + 1;
                    ans_r = r;
                }

                if (partialSum < min_sum) {
                    min_sum = partialSum;
                    min_pos = r;
                }
            }

            return new int[]{ans_l, ans_r, partialSum};
        }
    }

    /**
     * O(n*lg(n))
     */
    static class Finder {
        private int[] find(int[] array) {
            return find(array, 0, array.length - 1);
        }

        private int[] find(int[] array, int left, int right) {
            if (left >= right) {
                return new int[]{left, right, array[left]};
            } else {
                int mid = (left + right) / 2;

                int[] leftSubArray = find(array, left, mid);
                int[] rightSubArray = find(array, mid + 1, right);

                int[] crossArray = findCrossArray(array, left, mid, right);

                if (leftSubArray[2] >= rightSubArray[2] && leftSubArray[2] >= crossArray[2]) {
                    return leftSubArray;
                } else if (rightSubArray[2] >= leftSubArray[2] && rightSubArray[2] >= crossArray[2]) {
                    return rightSubArray;
                } else {
                    return crossArray;
                }
            }
        }

        private int[] findCrossArray(int[] array, int start, int mid, int end) {
            int leftSum = Integer.MIN_VALUE;
            int sum = 0;
            int left = -1;
            for (int i = mid; i >= start; i--) {
                sum += array[i];
                if (sum > leftSum) {
                    leftSum = sum;
                    left = i;
                }
            }

            sum = 0;
            int rightSum = Integer.MIN_VALUE;
            int right = -1;
            for (int i = mid + 1; i <= end; i++) {
                sum += array[i];
                if (sum > rightSum) {
                    rightSum = sum;
                    right = i;
                }
            }

            return new int[]{left, right, leftSum + rightSum};
        }
    }
}
