package com.burakovv.math;

import java.util.Arrays;

public class Matrices {
    private Matrices() {}

    public static Matrix of(double[][] data) {
        return new MatrixWrapper(data, data[0].length);
    }

    private static class MatrixWrapper implements Matrix {
        private final double[][] data;
        private final int n;

        public MatrixWrapper(double[][] data, int n) {
            this.data = data;
            this.n = n;
        }

        @Override
        public int columns() {
            return data.length;
        }

        @Override
        public int rows() {
            return n;
        }

        @Override
        public double get(int r, int c) {
            return data[r][c];
        }

        @Override
        public String toString() {
            return "MatrixWrapper{" +
                    "data=" + Arrays.toString(data) +
                    ", n=" + n +
                    '}';
        }
    }
}
