package com.burakovv.math;

public class NormalizedVector extends ForwardingVector {
    public static final double EPSILON = 1e-3;
    private final double addition;
    private final double scaleFactor;

    public NormalizedVector(Vector delegate) {
        super(delegate);
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        double sum = 0d;
        for (int i = 0; i < delegate.size(); i++) {
            double value = delegate.get(i);
            if (value > max) {
                max = value;
            }
            if (value < min) {
                min = value;
            }
        }
        scaleFactor = Math.abs(max - min) > EPSILON ? 1d / (max - min) : 1d;
        addition = -sum * scaleFactor / delegate.size();
    }

    @Override
    public double get(int i) {
        return normalize(super.get(i));
    }

    public double normalize(double value) {
        return value * scaleFactor + addition;
    }

    public double getAddition() {
        return addition;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }
}
