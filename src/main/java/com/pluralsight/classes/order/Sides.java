package com.pluralsight.classes.order;

public class Sides {
    private String name;
    private double cost;

    public Sides(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return name + " - $" + cost;
    }

    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Side: ").append(name).append("\n");
        details.append("Cost: $").append(cost).append("\n");
        return details.toString();
    }
}
