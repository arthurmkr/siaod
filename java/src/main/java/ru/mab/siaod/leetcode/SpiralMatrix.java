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
        System.out.println(alg.spiralOrder(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}}));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>(matrix.length * matrix[0].length);

        int i = 0, j = 0;
        int di = 0, dj = 1;
        int top = -1, left = -1;
        int right =matrix[0].length, bottom = matrix.length;

        while (i < bottom && j < right) {
            res.add(matrix[i][j]);

            i += di;
            j += dj;

            if (j == right) {
                di = 1;
                dj = 0;
                j--;
                i++;
                top++;
            } else if (i == bottom) {
                di = 0;
                dj = -1;
                i--;
                j--;
                right--;
            } else if (j == left) {
                di = -1;
                dj = 0;
                j++;
                i--;
                bottom--;
            } else if (i == top) {
                di = 0;
                dj = 1;
                left++;
                i++;
                j++;
            }
        }

        return res;
    }
}
