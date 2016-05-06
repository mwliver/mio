package com.github.algorithm;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

public class ParserImpl implements Parser {

    private String function;
    private int paramsCount;

    public ParserImpl(String function, int paramsCount) {
        this.function = function;
        this.paramsCount = paramsCount;
    }

    public MyFunction getFunction() {
        final ParserImpl self = this;

        return new MyFunction() {
            String func = self.function;
            int paramsCount = self.paramsCount;

            public double f(double[] parameters) {
                Calculable calculable = null;
                ExpressionBuilder expressionBuilder = new ExpressionBuilder(func);
                try {
                    String[] params = new String[paramsCount];
                    for (int i = 0; i < paramsCount; i++) {
                        params[i] = "x" + i;
                    }
                    calculable = expressionBuilder.withVariableNames(params).build();
                    for (int i = 0; i < paramsCount; i++) {
                        calculable.setVariable("x" + i, parameters[i]);
                    }
                } catch (UnknownFunctionException | UnparsableExpressionException e) {
                    e.printStackTrace();
                }

                double res;

                if (calculable != null) {
                    res = calculable.calculate();
                } else {
                    res = Double.NaN;
                }

                return res;
            }
        };
    }

    @Override
    public String toString() {
        return function;
    }
}
