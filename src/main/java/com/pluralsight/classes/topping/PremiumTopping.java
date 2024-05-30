package com.pluralsight.classes.topping;

public class PremiumTopping implements Topping{

    private String name;
    private double extraCost;

    public PremiumTopping(String name, double extraCost) {
        this.name = name;
        this.extraCost = extraCost;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getCost() {
        return extraCost;
    }

    @Override
    public String toString() {
        return name + " ($" + String.format("%.2f", extraCost) + ")";
    }
}
