package com.burakovv.ml;

import java.util.Arrays;

public class GradientDescent {
    private static final double[] X1 = {1, 2, 3, 4, 5};
    private static final double[] Y = {3, 5, 7, 9, 11};
    private static final int M = X1.length;

    private static final double[] T0 = {0, 0};
    private static final double ALPHA = 0.03d;
    private static final int MAX_ITER = 10000;

    public static void main(String[] args) {
        double[] t = Arrays.copyOf(T0, T0.length);
        double[] tmp = Arrays.copyOf(T0, T0.length);
        for (int i = 0; i < MAX_ITER; i++) {
            double sum = 0;
            for (int j = 0; j < M; j++) {
                sum += t[0] + t[1] * X1[j] - Y[j];
            }
            tmp[0] = t[0] - ALPHA / M * sum;

            sum = 0;
            for (int j = 0; j < M; j++) {
                sum += (t[0] + t[1] * X1[j] - Y[j]) * X1[j];
            }
            tmp[1] = t[1] - ALPHA / M * sum;

            double[] tt = tmp;
            tmp = t;
            t = tt;
        }
        double cost = 0;
        for (int i = 0; i < M; i++) {
            double d = t[0] + t[1] * X1[i] - Y[i];
            cost += d * d;
        }
        cost /= 2 * M;
        System.out.println("Theta: " + Arrays.toString(t));
        System.out.println("Cost: " + cost);
    }
}
