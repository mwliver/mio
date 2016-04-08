package com.github.algorithm;

import java.util.Random;

public class MyRandom extends Random {
    private final Random r;

    public MyRandom(Random r) {
        this.r = r;
    }

    public double nextDouble(double left, double right) {
        return r.nextDouble() * (right - left) + left;
    }
}
