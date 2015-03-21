package com.burakovv.math;

public abstract class MappedVector extends ForwardingVector {
    public MappedVector(Vector delegate) {
        super(delegate);
    }

    @Override
    public double get(int i) {
        return map(i, super.get(i));
    }

    protected abstract double map(int index, double value);
}
