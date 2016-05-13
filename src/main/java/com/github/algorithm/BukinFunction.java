package com.github.algorithm;

public class BukinFunction implements MyFunction {
    @Override
    public double f(double[] x) {
        return 100 * Math.sqrt(Math.abs(x[1] - 0.01 * Math.pow(x[0], 2))) + 0.01 * Math.abs(x[0] + 10);
    }
}
