package com.burakovv.math;

public class Polynomial implements Function {
    private final Vector parameters;

    public Polynomial(Vector parameters) {
        this.parameters = parameters;
    }

    @Override
    public double eval(double argument) {
        double exp = 1d;
        double acc = 0;
        for (int i = 0, size = parameters.size(); i < size; i++) {
            acc += parameters.get(i) * exp;
            if (i < size - 1) {
                exp *= argument;
            }
        }
        return acc;
    }

    public Vector getParameters() {
        return parameters;
    }
}
