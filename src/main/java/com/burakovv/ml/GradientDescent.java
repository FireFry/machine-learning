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
    private static final double[] INITIAL_THETA = {0, 0};

    /**
     * Learning rate for gradient descent algorithm.
     */
    private static final double ALPHA = 0.1d;

    /**
     * Number of iterations to take finding optimal theta.
     */
    private static final int ITERATIONS = 1000000;

    public static void main(String[] args) {
        double[] theta = Arrays.copyOf(INITIAL_THETA, INITIAL_THETA.length);
        double[] buffer = new double[INITIAL_THETA.length];
        double[] hypothesis = new double[M];

        for (int i = 0; i < ITERATIONS; i++) {
            double[] prevTheta = theta;
            theta = buffer;
            calcHypothesis(prevTheta, hypothesis);
            calcNewTheta(prevTheta, hypothesis, theta);
            buffer = prevTheta;
        }

        System.out.println("Theta: " + Arrays.toString(theta));
        System.out.println("Cost: " + cost(theta));
    }

    private static void calcHypothesis(double[] theta, double[] hypothesis) {
        for (int i = 0; i < M; i++) {
            hypothesis[i] = 0;
            for (int j = 0; j < N + 1; j++) {
                hypothesis[i] += theta[j] * X[j][i];
            }
        }
    }

    private static void calcNewTheta(double[] oldTheta, double[] hypothesis, double[] newTheta) {
        for (int j = 0; j < N + 1; j++) {
			double sigma = 0;
			for (int i = 0; i < M; i++) {
				sigma += (hypothesis[i] - Y[i]) * X[j][i];
			}
			newTheta[j] = oldTheta[j] - ALPHA / M * sigma;
		}
    }

    private static double cost(double[] theta) {
        double sigma = 0;
        for (int i = 0; i < M; i++) {
            double polynomial = 0;
            for (int j = 0; j < N + 1; j++) {
                polynomial += theta[j] * X[j][i];
            }
            double delta = polynomial - Y[i];
            sigma += delta * delta;
        }
        return sigma / (2 * M);
    }
}
