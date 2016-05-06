package com.github.view;

import com.github.algorithm.MyFunction;

public class ComboboxItem {
    private MyFunction function;
    private String name;
    private double left;
    private double right;

    public ComboboxItem(MyFunction function, String name, double left, double right) {
        this.function = function;
        this.name = name;
        this.left = left;
        this.right = right;
    }

    public MyFunction getFunction() {
        return function;
    }

    public String getName() {
        return name;
    }

    public double getLeft() {
        return left;
    }

    public double getRight() {
        return right;
    }

    @Override
    public String toString() {
        return name;
    }
}
