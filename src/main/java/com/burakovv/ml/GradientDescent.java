package com.burakovv.ml;

import com.burakovv.math.ArrayVector;
import com.burakovv.math.Matrices;
import com.burakovv.math.Matrix;
import com.burakovv.math.Vector;
import com.burakovv.math.Vectors;

public class GradientDescent {

    /**
     * Features matrix X[N + 1][M] where N is number of features and M is number of training examples.
     * X[0] vector represents extra feature required by gradient descent algorithm and must be equal to 1 for each training example.
     */
    private static final Matrix X = Matrices.of(new double[][]{
            {1, 1, 1, 1, 1}, // must be filled with 1
            {1, 2, 3, 4, 5},
            {1, 4, 9, 16, 25}, // = X[1] ^ 2
    });

    /**
     * Training examples output vector.
     */
    private static final Vector Y = Vectors.of(new double[]{0, 1, 4, 9, 16});

    /**
     * Number of features.
     */
    private static final int N = (X.columns() - 1);

    /**
     * Number of training examples.
     */
    private static final int M = X.rows();

    /**
     * Initial theta is the starting point for gradient descent algorithm.
     */
    private static final Vector INITIAL_THETA = Vectors.of(new double[]{0, 0, 0});

    /**
     * Learning rate for gradient descent algorithm.
     */
    private static final double ALPHA = 0.003d;

    /**
     * Number of iterations to take finding optimal theta.
     */
    private static final int ITERATIONS = 1000000;

    private final Matrix x;
    private final Vector y;
    private final ArrayVector hypothesis;
    private ArrayVector theta;
    private ArrayVector newTheta;

    public static void main(String[] args) {
        GradientDescent gradientDescent = new GradientDescent(X, Y, INITIAL_THETA);

        for (int i = 0; i < ITERATIONS; i++) {
            gradientDescent.iterate();
        }

        System.out.println("Theta: " + gradientDescent.theta);
        System.out.println("Cost: " + gradientDescent.cost());
    }

    public GradientDescent(Matrix x, Vector y, Vector initialTheta) {
        this.x = x;
        this.y = y;
        this.theta = ArrayVector.copyOf(initialTheta);
        this.newTheta = new ArrayVector(theta.size());
        this.hypothesis = new ArrayVector(M);
    }

    public void iterate() {
        calcHypothesis();
        calcNewTheta();
        swapTheta();
    }

    private void swapTheta() {
        ArrayVector tmp = theta;
        theta = newTheta;
        newTheta = tmp;
    }

    private void calcHypothesis() {
        for (int i = 0; i < M; i++) {
            hypothesis.set(i, 0);
            for (int j = 0; j < N + 1; j++) {
                hypothesis.set(i, hypothesis.get(i) + theta.get(j) * x.get(j, i));
            }
        }
    }

    private void calcNewTheta() {
        for (int j = 0; j < N + 1; j++) {
            double sigma = 0;
            for (int i = 0; i < M; i++) {
                sigma += (hypothesis.get(i) - y.get(i)) * x.get(j, i);
            }
            newTheta.set(j, theta.get(j) - ALPHA / M * sigma);
        }
    }

    private double cost() {
        double sigma = 0;
        for (int i = 0; i < M; i++) {
            double polynomial = 0;
            for (int j = 0; j < N + 1; j++) {
                polynomial += theta.get(j) * x.get(j, i);
            }
            double delta = polynomial - y.get(i);
            sigma += delta * delta;
        }
        return sigma / (2 * M);
    }
}
