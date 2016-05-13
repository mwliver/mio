package com.github.algorithm;

public class BoothFunction implements MyFunction {
    @Override
    public double f(double[] x) {
        return Math.pow(x[0] + 2 * x[1] - 7, 2) + Math.pow(2 * x[0] + x[1] - 5, 2);
    }
}
