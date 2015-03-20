package com.burakovv.math;

import java.util.Arrays;

public class Vectors {
    private Vectors() {}

    public static Vector of(double[] data) {
        return new VectorWrapper(data);
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
