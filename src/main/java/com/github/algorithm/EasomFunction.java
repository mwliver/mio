package com.github.algorithm;

public class EasomFunction implements MyFunction {
    @Override
    public double f(double[] x) {
        return -Math.cos(Math.toRadians(x[0])) * Math.cos(Math.toRadians(x[1])) * Math.exp(-(Math.pow(x[0] - Math.PI, 2) + Math.pow(x[1] - Math.PI, 2)));
    }
}
