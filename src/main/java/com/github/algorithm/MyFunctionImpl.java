package com.github.algorithm;

/**
 * Copyright (C) Coderion sp. z o.o
 */
public class MyFunctionImpl implements MyFunction {
    public double f(double[] parameters) {
        if (parameters.length < 2) {
            throw new IllegalArgumentException();
        }

        return -Math.pow(parameters[0], 2) - Math.pow(parameters[1], 2) + 2;
    }
}