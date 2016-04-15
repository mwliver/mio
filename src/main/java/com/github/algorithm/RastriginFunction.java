package com.github.algorithm;

public class RastriginFunction implements MyFunction {
    @Override
    public double f(double[] x) {
        return 20 + Math.pow(x[0], 2) + Math.pow(x[1], 2) - 10 * (Math.cos(Math.toRadians(2 * Math.PI * x[0])) - Math.cos(Math.toRadians(2 * Math.PI * x[1])));
    }
}
