package com.burakovv.math;

import java.util.ArrayList;
import java.util.List;

public class VectorsMatrix implements Matrix {
    private final int rows;
    private final List<Vector> vectorList;

    private VectorsMatrix(int rows, List<Vector> vectorList) {
        this.rows = rows;
        this.vectorList = vectorList;
    }

    @Override
    public int rows() {
        return rows;
    }

    @Override
    public int columns() {
        return vectorList.size();
    }

    @Override
    public double get(int r, int c) {
        return vectorList.get(c).get(r);
    }

    public static class Builder {
        private final int rows;
        private List<Vector> vectorList = new ArrayList<>();

        public Builder(int rows) {
            this.rows = rows;
        }

        public void add(Vector vector) {
            if (vector.size() < rows) {
                throw new IllegalArgumentException();
            }
            vectorList.add(vector);
        }

        public void addAll(Vector... vectors) {
            for (Vector vector : vectors) {
                add(vector);
            }
        }

        public VectorsMatrix build() {
            return new VectorsMatrix(rows, vectorList);
        }
    }
}
