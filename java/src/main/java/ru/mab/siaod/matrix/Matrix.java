package ru.mab.siaod.matrix;

import lombok.Getter;

public class Matrix {
    @Getter
    private int rows;
    @Getter
    private int columns;
    private int data[];

    public Matrix(int rows, int columns) {
        this.data = new int[rows * columns];
        this.rows = rows;
        this.columns = columns;
    }

    public Matrix(int[][] initData) {
        this(initData.length, initData[0].length);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.data[i * columns + j] = initData[i][j];
            }
        }
    }

    public void set(int row, int column, int value) {
        checkIndexes(row, column);
        data[row * columns + column] = value;
    }

    public int get(int row, int column) {
        checkIndexes(row, column);

        return data[row * columns + column];
    }

    public Matrix multiple(Matrix matrix) {
        if (getColumns() != matrix.getRows()) {
            throw new IllegalArgumentException("Incompatible matrix");
        }

        Matrix newMatrix = new Matrix(getRows(), matrix.getColumns());

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                int c = 0;
                for (int k = 0; k < getColumns(); k++) {
                    c += get(i, k) * matrix.get(k, j);
                }

                newMatrix.set(i, j, c);
            }
        }

        return newMatrix;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Matrix {");
        for (int i = 0; i < data.length; i++) {
            if (i % columns == 0) {
                builder.append('\n');
            }
            builder.append(data[i]).append('\t');
        }
        builder.append("\n}");
        return builder.toString();
    }

    private void checkIndexes(int row, int column) {
        if (row >= rows || row < 0) {
            throw new IllegalArgumentException("Row[" + row + "] is out of bound");
        }

        if (column >= columns || column < 0) {
            throw new IllegalArgumentException("Column[" + column + "] is out of bound");
        }
    }
}
