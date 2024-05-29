package com.pluralsight.classes.topping;

public class RegularTopping extends Topping {
    private double cost;

    public RegularTopping(String name, double cost) {
        super(name);
        this.cost = cost;
    }

    @Override
    public double getCost() {
        return this.cost;
    }
}
