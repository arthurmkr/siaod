package ru.mab.siaod.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. Spiral Matrix
 */
public class SpiralMatrix {
    public static void main(String[] args) {
        SpiralMatrix alg = new SpiralMatrix();
//        System.out.println(alg.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
//        System.out.println(alg.spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
//        System.out.println(alg.spiralOrder(new int[][]{{1}, {2}}));
        System.out.println(alg.spiralOrder(new int[][]{{1, 2}, {3, 4}}));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int total = matrix.length * matrix[0].length;
        List<Integer> res = new ArrayList<>(total);

        int i = 0, j = 0;
        int top = -1, left = -1;
        int right = matrix[0].length, bottom = matrix.length;

        int[][] deltas = {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };
        int k = 0;
        while (0 <= i && i < bottom && 0 <= j && j < right && total > res.size()) {
            res.add(matrix[i][j]);

            i += deltas[k % 4][0];
            j += deltas[k % 4][1];

            if (j == right) {
                k++;
                j--;
                i++;
                top++;
            } else if (i == bottom) {
                k++;
                i--;
                j--;
                right--;
            } else if (j == left) {
                k++;
                j++;
                i--;
                bottom--;
            } else if (i == top) {
                k++;
                left++;
                i++;
                j++;
            }
        }

        return res;
    }
}
