package com.burakovv;

import com.burakovv.math.MappedVector;
import com.burakovv.math.Polynomial;
import com.burakovv.math.Vector;
import com.burakovv.math.Vectors;
import com.burakovv.ml.GradientDescent;

public class GradientDescentApp {
    public static void main(String[] args) {
        Vector x = Vectors.range(1, 5);
        Polynomial polynomial = new Polynomial(Vectors.of(1, -2, 1));
        Vector y = Vectors.map(x, polynomial);
        Vector squaredX = new MappedVector(x) {
            @Override
            protected double map(int index, double value) {
                return value * value;
            }
        };

        Vector initialTheta = Vectors.of(0, 0, 0);

        GradientDescent gradientDescent = new GradientDescent(0.003d, initialTheta, y, x, squaredX);
        for (int i = 0; i < 1000000; i++) {
            gradientDescent.iterate();
        }

        System.out.println("Theta: " + gradientDescent.getTheta());
        System.out.println("Cost: " + gradientDescent.cost());
    }
}
