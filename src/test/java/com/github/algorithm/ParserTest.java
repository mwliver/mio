package com.github.algorithm;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    private ParserImpl parser;

    @Test
    public void parserTest_TC001() {
        String function = "x0^2";
        parser = new ParserImpl(function, 1);
        MyFunction myFunction = parser.getFunction();
        double[] params = new double[]{2};
        double result = myFunction.f(params);
        assertEquals(4.0, result, 0.0);
    }

    @Test
    public void parserTest_TC002() {
        String function = "x0^2+2";
        parser = new ParserImpl(function, 1);
        MyFunction myFunction = parser.getFunction();
        double[] params = new double[]{3};
        double result = myFunction.f(params);
        assertEquals(11.0, result, 0.0);
    }

    @Test
    public void parserTest_TC003() {
        String function = "-(x0^2)+2";
        parser = new ParserImpl(function, 1);
        MyFunction myFunction = parser.getFunction();
        double[] params = new double[]{3};
        double result = myFunction.f(params);
        assertEquals(-7.0, result, 0.0);
    }

    @Test
    public void parserTest_TC004() {
        String function = "(x0^2)+(x1^2)";
        parser = new ParserImpl(function, 2);
        MyFunction myFunction = parser.getFunction();
        double[] params = new double[]{2, 2};
        double result = myFunction.f(params);
        assertEquals(8.0, result, 0.0);
    }

    @Test
    public void parserTest_TC005() {
        String function = "sin(x0)+sin(x1)";
        parser = new ParserImpl(function, 2);
        MyFunction myFunction = parser.getFunction();
        double[] params = new double[]{1, 2};
        double result = myFunction.f(params);
        assertEquals(1.75, result, 0.01);
    }
}
