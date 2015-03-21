package com.burakovv.math;

public class ForwardingVector implements Vector {
    private final Vector delegate;

    public ForwardingVector(Vector delegate) {
        this.delegate = delegate;
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public double get(int i) {
        return delegate.get(i);
    }
}
