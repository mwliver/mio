package com.github.algorithm;

public class MatyasFunction implements MyFunction {
    @Override
    public double f(double[] x) {
        return 0.26 * (Math.pow(x[0], 2) + Math.pow(x[1], 2)) - 0.48 * x[0] * x[1];
    }
}
