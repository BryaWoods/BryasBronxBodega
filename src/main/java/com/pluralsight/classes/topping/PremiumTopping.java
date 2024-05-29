package com.pluralsight.classes.topping;

public class PremiumTopping extends Topping{

    private double extraCost;

    public PremiumTopping(String name, double extraCost) {
        super(name);
        this.extraCost = extraCost;
    }

    @Override
    public double getCost() {
        return this.extraCost;
    }
}
