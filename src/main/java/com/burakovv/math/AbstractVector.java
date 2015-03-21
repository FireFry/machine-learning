package com.burakovv.math;

public abstract class AbstractVector implements Vector {
    @Override
    public String toString() {
        return Vectors.toString(this);
    }
}
