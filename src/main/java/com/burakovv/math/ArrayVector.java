package com.burakovv.math;

import java.util.Arrays;

public class ArrayVector implements Vector {
    private final double[] data;

    public static ArrayVector copyOf(Vector vector) {
        ArrayVector copy = new ArrayVector(vector.size());
        for (int i = 0; i < vector.size(); i++) {
            copy.set(i, vector.get(i));
        }
        return copy;
    }

    public void set(int i, double v) {
        data[i] = v;
    }

    public ArrayVector(int size) {
        data = new double[size];
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
        return "ArrayVector{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
