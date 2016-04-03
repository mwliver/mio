package com.github.model;

import com.github.algorithm.Function;
import com.github.algorithm.Parser;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

/**
 * Copyright (C) Coderion sp. z o.o
 */
public class ParserImpl implements Parser {

    private String function;
    private int paramsCount;

    public ParserImpl(String function, int paramsCount) {
        this.function = function;
        this.paramsCount = paramsCount;
    }

    public Function getFunction() {
        final ParserImpl self = this;

        Function function = new Function() {
            String function = self.function;
            int paramsCount = self.paramsCount;

            public double compute(double[] parameters) {
                Calculable calculable = null;
                ExpressionBuilder expressionBuilder = new ExpressionBuilder(function);
                try {
                    String[] params = new String[paramsCount];
                    for (int i = 0; i < paramsCount; i++) {
                        params[i] = "x" + i;
                    }
                    calculable = expressionBuilder.withVariableNames(params).build();
                    for (int i = 0; i < paramsCount; i++) {
                        calculable.setVariable("x" + i, parameters[i]);
                    }
                } catch (UnknownFunctionException e) {
                    e.printStackTrace();
                } catch (UnparsableExpressionException e) {
                    e.printStackTrace();
                }

                return calculable.calculate();
            }
        };

        return function;
    }
}
