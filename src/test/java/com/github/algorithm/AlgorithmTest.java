package com.github.algorithm;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AlgorithmTest {

    @Test
    public void algorithmTest_TC001() {
        Cooling cooling = t -> t * 0.999;
        MyFunction myFunction = new MyFunctionImpl();

        double temperature = 1000;
        double precision = 0.001;

        int dimencion = 2;
        double leftLimit = -10000;
        double rightLimit = 10000;

        SimulatedAnnealing sa = new SimulatedAnnealing();

        double[] result = sa.simulatedAnnealing(myFunction, cooling, temperature, precision, dimencion, leftLimit, rightLimit);

        assertTrue(9900 <= Math.abs(result[0]));
        assertTrue(9900 <= Math.abs(result[1]));
    }
}
