package com.github.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.function.BiFunction;

public class SimulatedAnnealing {

    private Queue<double[]> bests;

    private Queue<double[]> currents;

    public Queue<double[]> getBests() {
        return bests;
    }

    public Queue<double[]> getCurrents() {
        return currents;
    }

    public double[] simulatedAnnealing(MyFunction function, Cooling step, double T, double precision, int dimension, double leftLimit, double rightLimit) {
        bests = new LinkedList<>();
        currents = new LinkedList<>();

        MyRandom r = new MyRandom(new Random());

        double[] current = new double[dimension];
        double[] next = new double[dimension];
        double[] best = new double[dimension];
        double deltaE = 0;
        double p = 0;

        for (int i = 0; i < dimension; i++) {
            current[i] = r.nextDouble(leftLimit, rightLimit);
            best[i] = current[i];
        }

        BiFunction<Double, Double, Boolean> f = (a, b) -> (a <= b);

        while (true) {
            if (T <= precision) {
                return best;
            }
            for (int i = 0; i < dimension; i++) {
                next[i] = r.nextDouble(leftLimit, rightLimit);
            }
            deltaE = function.f(current) - function.f(next);
            p = Math.exp(deltaE / T);
            T = step.f(T);
            if (deltaE > 0) {
                System.arraycopy(next, 0, current, 0, dimension);
            } else {
                if (f.apply(p, r.nextDouble())) {
                    System.arraycopy(next, 0, current, 0, dimension);
                }
            }
            if (function.f(current) < function.f(best)) {
                System.arraycopy(current, 0, best, 0, dimension);
            }

            double[] currentArr = new double[dimension];
            System.arraycopy(current, 0, currentArr, 0, dimension);
            currents.add(currentArr);

            double[] bestArr = new double[dimension];
            System.arraycopy(best, 0, bestArr, 0, dimension);
            bests.add(bestArr);
        }
    }
}
