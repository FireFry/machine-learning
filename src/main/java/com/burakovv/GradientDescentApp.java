package com.burakovv;

import com.burakovv.math.MappedVector;
import com.burakovv.math.Polynomial;
import com.burakovv.math.NormalizedVector;
import com.burakovv.math.Vector;
import com.burakovv.math.Vectors;
import com.burakovv.ml.GradientDescent;

public class GradientDescentApp {
    public static void main(String[] args) {
        Vector x = Vectors.range(1, 500);
        Polynomial polynomial = new Polynomial(Vectors.of(1, -2, 1));
        Vector y = Vectors.map(x, polynomial);
        Vector squaredX = new MappedVector(x) {
            @Override
            protected double map(int index, double value) {
                return value * value;
            }
        };

        Vector initialTheta = Vectors.of(0, 0, 0);

        NormalizedVector xnorm = new NormalizedVector(x);
        NormalizedVector sxnorm = new NormalizedVector(squaredX);
        GradientDescent gradientDescent = new GradientDescent(
                1d,
                initialTheta,
                y,
                xnorm,
                sxnorm
        );
        for (int i = 0; i < 10000; i++) {
            gradientDescent.iterate();
        }

        System.out.println("Theta: " + gradientDescent.getTheta());
        System.out.println("Theta error: " + Vectors.minus(polynomial.getParameters(), gradientDescent.getTheta()));
        System.out.println("Cost: " + gradientDescent.cost());

        System.out.println("Expected result: " + polynomial.eval(5173d));
        System.out.println("Actual result: " + (
                gradientDescent.getTheta().get(0) +
                gradientDescent.getTheta().get(1) * (5173d * xnorm.getScaleFactor() + xnorm.getAddition()) +
                gradientDescent.getTheta().get(2) * (5173d * 5173d * sxnorm.getScaleFactor() + sxnorm.getAddition())
        ));

        System.out.println("Denormalized theta: " + Vectors.of(
                gradientDescent.getTheta().get(0) + gradientDescent.getTheta().get(1) * xnorm.getAddition() + gradientDescent.getTheta().get(2) * sxnorm.getAddition(),
                gradientDescent.getTheta().get(1) * xnorm.getScaleFactor(),
                gradientDescent.getTheta().get(2) * sxnorm.getScaleFactor()
        ));
    }
}
