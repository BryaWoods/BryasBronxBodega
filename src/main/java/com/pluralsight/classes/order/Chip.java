package com.pluralsight.classes.order;

public class Chip {

    private String type;
    private double cost;

    public Chip(String type, double cost) {
        this.type = type;
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Chip: ").append(type).append("\n");
        details.append("Cost: $").append(cost).append("\n");
        return details.toString();
    }

    @Override
    public String toString() {
        return type + " ($" + String.format("%.2f", cost) + ")";
    }
}
