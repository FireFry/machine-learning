package com.burakovv.ml;

import com.burakovv.math.AbstractVector;
import com.burakovv.math.ArrayVector;
import com.burakovv.math.Matrix;
import com.burakovv.math.Vector;
import com.burakovv.math.Vectors;
import com.burakovv.math.VectorsMatrix;

import java.util.ArrayList;
import java.util.List;

public class GradientDescent {

    /**
     * Features matrix X[n][m] where n is number of features and m is number of training examples.
     * X[0] vector represents extra feature required by gradient descent algorithm and must be equal to 1 for each training example.
     */
    private final List<Vector> x;

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

    private final Vector thetaView = new AbstractVector() {
        @Override
        public int size() {
            return theta.size();
        }

        @Override
        public double get(int i) {
            return theta.get(i);
        }
    };

    public GradientDescent(double alpha, Vector initialTheta, Vector y, Vector x1, Vector... xs) {
        this.x = new ArrayList<Vector>() {{
            add(Vectors.only(1d, x1.size()));
            add(x1);
            for (Vector x : xs) {
                add(x);
            }
        }};
        this.y = y;
        this.n = x.size();
        this.m = x1.size();
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
                hypothesis.set(i, hypothesis.get(i) + theta.get(j) * x.get(j).get(i));
            }
        }
    }

    private void calcNewTheta() {
        for (int j = 0; j < n; j++) {
            double sigma = 0;
            for (int i = 0; i < m; i++) {
                sigma += (hypothesis.get(i) - y.get(i)) * x.get(j).get(i);
            }
            nextTheta.set(j, theta.get(j) - alpha / m * sigma);
        }
    }

    public double cost() {
        double sigma = 0;
        for (int i = 0; i < m; i++) {
            double polynomial = 0;
            for (int j = 0; j < n; j++) {
                polynomial += theta.get(j) * x.get(j).get(i);
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
