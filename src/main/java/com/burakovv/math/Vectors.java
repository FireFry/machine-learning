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
        return new AbstractVector() {
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

    public static String toString(Vector vector) {
        StringBuilder sb = new StringBuilder().append('[');
        for (int i = 0, size = vector.size(); i < size; i++) {
            sb.append(vector.get(i));
            if (i < size - 1) {
                sb.append(", ");
            } else {
                sb.append(']');
            }
        }
        return sb.toString();
    }

    public static Vector minus(Vector v1, Vector v2) {
        return new AbstractVector() {
            @Override
            public int size() {
                return v1.size();
            }

            @Override
            public double get(int i) {
                return v1.get(i) - v2.get(i);
            }
        };
    }

    public static Vector only(double val, int size) {
        return new AbstractVector() {
            @Override
            public int size() {
                return size;
            }

            @Override
            public double get(int i) {
                return val;
            }
        };
    }

    private static class VectorWrapper extends AbstractVector {
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
    }
}
