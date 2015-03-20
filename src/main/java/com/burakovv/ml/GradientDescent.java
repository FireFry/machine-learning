package com.burakovv.ml;

import java.util.Arrays;

public class GradientDescent {

    /**
     * Features matrix X[N + 1][M] where N is number of features and M is number of training examples.
     * X[0] vector represents extra feature required by gradient descent algorithm and must be equal to 1 for each training example.
     */
    private static final double[][] X = {
            {1, 1, 1, 1, 1}, // must be filled with 1
            {1, 2, 3, 4, 5},
            {1, 4, 9, 16, 25}, // = X[1] ^ 2
    };

    /**
     * Training examples output vector.
     */
    private static final double[] Y = {0, 1, 4, 9, 16};

    /**
     * Number of features.
     */
    private static final int N = (X.length - 1);

    /**
     * Number of training examples.
     */
    private static final int M = X[0].length;

    /**
     * Initial theta is the starting point for gradient descent algorithm.
     */
    private static final double[] INITIAL_THETA = {0, 0, 0};

    /**
     * Learning rate for gradient descent algorithm.
     */
    private static final double ALPHA = 0.003d;

    /**
     * Number of iterations to take finding optimal theta.
     */
    private static final int ITERATIONS = 1000000;

    private final double[][] x;
    private final double[] y;
    private final double[] hypothesis;
    private double[] theta;
    private double[] newTheta;

    public static void main(String[] args) {
        GradientDescent gradientDescent = new GradientDescent(X, Y, INITIAL_THETA);

        for (int i = 0; i < ITERATIONS; i++) {
            gradientDescent.iterate();
        }

        System.out.println("Theta: " + Arrays.toString(gradientDescent.theta));
        System.out.println("Cost: " + gradientDescent.cost());
    }

    public GradientDescent(double[][] x, double[] y, double[] initialTheta) {
        this.x = x;
        this.y = y;
        this.theta = Arrays.copyOf(initialTheta, initialTheta.length);
        this.newTheta = new double[theta.length];
        this.hypothesis = new double[M];
    }

    public void iterate() {
        calcHypothesis();
        calcNewTheta();
        swapTheta();
    }

    private void swapTheta() {
        double[] tmp = theta;
        theta = newTheta;
        newTheta = tmp;
    }

    private void calcHypothesis() {
        for (int i = 0; i < M; i++) {
            hypothesis[i] = 0;
            for (int j = 0; j < N + 1; j++) {
                hypothesis[i] += theta[j] * x[j][i];
            }
        }
    }

    private void calcNewTheta() {
        for (int j = 0; j < N + 1; j++) {
            double sigma = 0;
            for (int i = 0; i < M; i++) {
                sigma += (hypothesis[i] - y[i]) * x[j][i];
            }
            newTheta[j] = theta[j] - ALPHA / M * sigma;
        }
    }

    private double cost() {
        double sigma = 0;
        for (int i = 0; i < M; i++) {
            double polynomial = 0;
            for (int j = 0; j < N + 1; j++) {
                polynomial += theta[j] * x[j][i];
            }
            double delta = polynomial - y[i];
            sigma += delta * delta;
        }
        return sigma / (2 * M);
    }
}
