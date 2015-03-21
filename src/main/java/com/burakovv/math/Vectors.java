package com.burakovv.math;

import java.util.Arrays;

public class Vectors {
    private Vectors() {}

    public static Vector of(double... data) {
        return new VectorWrapper(data);
    }

    public static Vector map(Vector vector, final Function function) {
        return new MappedVector(vector) {
            @Override
            protected double map(int index, double value) {
                return function.eval(value);
            }
        };
    }

    public static Vector range(int min, int max) {
        return range(min, max, 1);
    }

    private static Vector range(int min, int max, int step) {
        final int size = (max - min) / step;
        return new Vector() {
            @Override
            public int size() {
                return size;
            }

            @Override
            public double get(int i) {
                if (i < 0 || i >= size) {
                    throw new IndexOutOfBoundsException();
                }
                return min + step * i;
            }
        };
    }

    private static class VectorWrapper implements Vector {
        private double[] data;

        public VectorWrapper(double[] data) {
            this.data = data;
        }

        @Override
        public int size() {
            return data.length;
        }

        @Override
        public double get(int i) {
            return data[i];
        }

        @Override
        public String toString() {
            return "VectorWrapper{" +
                    "data=" + Arrays.toString(data) +
                    '}';
        }
    }
}
