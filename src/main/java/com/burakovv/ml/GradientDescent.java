package com.burakovv.ml;

import com.burakovv.math.ArrayVector;
import com.burakovv.math.Matrix;
import com.burakovv.math.Vector;

public class GradientDescent {

    /**
     * Features matrix X[n][m] where n is number of features and m is number of training examples.
     * X[0] vector represents extra feature required by gradient descent algorithm and must be equal to 1 for each training example.
     */
    private final Matrix x;

    /**
     * Training examples output vector.
     */
    private final Vector y;

    /**
     * Number of features.
     */
    private final int n;

    /**
     * Number of training examples.
     */
    private final int m;

    /**
     * Learning rate for gradient descent algorithm.
     */
    private final double alpha;

    private final ArrayVector hypothesis;

    private ArrayVector theta;

    private ArrayVector nextTheta;

    private final Vector thetaView = new Vector() {
        @Override
        public int size() {
            return theta.size();
        }

        @Override
        public double get(int i) {
            return theta.get(i);
        }

        @Override
        public String toString() {
            return theta.toString();
        }
    };

    public GradientDescent(double alpha, Vector initialTheta, Vector y, Vector x1, Vector... xs) {
        this(
                new Matrix() {
                    @Override
                    public int rows() {
                        return x1.size();
                    }

                    @Override
                    public int columns() {
                        return xs.length + 2;
                    }

                    @Override
                    public double get(int r, int c) {
                        switch (c) {
                            case 0:
                                return 1;
                            case 1:
                                return x1.get(r);
                            default:
                                return xs[c - 2].get(r);
                        }
                    }
                },
                y,
                initialTheta,
                alpha
        );
    }

    public GradientDescent(Matrix x, Vector y, Vector initialTheta, double alpha) {
        this.x = x;
        this.y = y;
        this.n = x.columns();
        this.m = x.rows();
        this.alpha = alpha;
        this.theta = ArrayVector.copyOf(initialTheta);
        this.nextTheta = new ArrayVector(theta.size());
        this.hypothesis = new ArrayVector(m);
    }

    public void iterate() {
        calcHypothesis();
        calcNewTheta();
        swapTheta();
    }

    private void swapTheta() {
        ArrayVector tmp = theta;
        theta = nextTheta;
        nextTheta = tmp;
    }

    private void calcHypothesis() {
        for (int i = 0; i < m; i++) {
            hypothesis.set(i, 0);
            for (int j = 0; j < n; j++) {
                hypothesis.set(i, hypothesis.get(i) + theta.get(j) * x.get(i, j));
            }
        }
    }

    private void calcNewTheta() {
        for (int j = 0; j < n; j++) {
            double sigma = 0;
            for (int i = 0; i < m; i++) {
                sigma += (hypothesis.get(i) - y.get(i)) * x.get(i, j);
            }
            nextTheta.set(j, theta.get(j) - alpha / m * sigma);
        }
    }

    public double cost() {
        double sigma = 0;
        for (int i = 0; i < m; i++) {
            double polynomial = 0;
            for (int j = 0; j < n; j++) {
                polynomial += theta.get(j) * x.get(i, j);
            }
            double delta = polynomial - y.get(i);
            sigma += delta * delta;
        }
        return sigma / (2 * m);
    }

    public Vector getTheta() {
        return thetaView;
    }
}
