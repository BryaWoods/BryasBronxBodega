package com.pluralsight.classes.topping;

public class Sides implements Topping {

    private String name;

    private double cost;

    public Sides(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getCost() {
        return cost;
    }
}
