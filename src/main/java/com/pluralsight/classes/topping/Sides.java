package com.pluralsight.classes.topping;

public class Sides extends Topping {

    private String name;

    private double cost;

    public Sides(String name, String name1, double cost) {
        super(name);
        this.name = name1;
        this.cost = cost;
    }

    @Override
    public double getCost() {
        return this.cost;
    }
}
