package com.github.algorithm;

public class HimmelblauFunction implements MyFunction {

    @Override
    public double f(double[] x) {
        return Math.pow(Math.pow(x[0], 2) + x[1] - 11, 2) + Math.pow(x[0] + Math.pow(x[1], 2) - 7, 2);
    }
}
