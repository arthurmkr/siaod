package ru.mab.siaod.matrix;

import ru.mab.siaod.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class Multiple {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            List<Matrix> matrixs = generateMatrixs(10, 500);
            long time = System.currentTimeMillis();
            dynamicMultiple(matrixs);
            System.out.println("Dynamic Time: " + (System.currentTimeMillis() - time));

            time = System.currentTimeMillis();
            singleMultiple(matrixs);
            System.out.println("Single Time: " + (System.currentTimeMillis() - time));
        }
    }

    private static void singleMultiple(List<Matrix> matrixs) {
        Matrix cur = matrixs.get(0);
        for (int i = 1; i < matrixs.size(); i++) {
            cur = cur.multiple(matrixs.get(i));
        }
    }

    private static void dynamicMultiple(List<Matrix> matrixs) {
        int[] p = new int[matrixs.size() + 1];

        p[0] = matrixs.get(0).getRows();
        for (int i = 0; i < matrixs.size(); i++) {
            p[i + 1] = matrixs.get(i).getColumns();
        }

        List<int[][]> ints = matrixChainOrder(p);

        Matrix res = mult(matrixs, ints.get(1), 0, matrixs.size() - 1);
    }

    private static Matrix mult(List<Matrix> matrixs, int[][] s, int i, int j) {
        if (i == j) {
            return matrixs.get(i);
        }

        Matrix a = mult(matrixs, s, i, s[i][j]);
        Matrix b = mult(matrixs, s, s[i][j] + 1, j);

        return a.multiple(b);
    }

    public static List<int[][]> matrixChainOrder(int[] p) {
        int[][] m = new int[p.length - 1][p.length - 1];
        int[][] s = new int[p.length - 1][p.length - 1];

        int l;
        for (l = 2; l < p.length; l++) {
            int i;
            for (i = 0; i < p.length - l; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;

                int k;
                for (k = i; k < j; k++) {
//                    if (r[i][k + 1][j + 1] == 0) {
//                        r[i][k + 1][j + 1] = p[i] * p[k + 1] * p[j + 1];
//                    }
                    int q = m[i][k] + m[k + 1][j] +  p[i] * p[k + 1] * p[j + 1];

                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }

//                System.out.println("k: " + k);
            }
//            System.out.println("l: " + l + ", i: " + i);
        }

//        System.out.println("l: " + l);
        return List.of(m, s);
    }

    private static List<Matrix> generateMatrixs(int count, int max) {
        int row = RandomUtil.nextNaturalInt(max);

        List<Matrix> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int column = RandomUtil.nextNaturalInt(max);
            result.add(generateMatrix(row, column));

            row = column;
        }

        return result;
    }

    private static Matrix generateMatrix(int rows, int columns) {
        Matrix matrix = new Matrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix.set(i, j, RandomUtil.nextInt(20));
            }
        }

        return matrix;
    }
}
