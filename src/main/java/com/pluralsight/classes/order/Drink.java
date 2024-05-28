package com.pluralsight.classes.order;

public class Drink {

    private String size;
    private String type;
    private double cost;

    public Drink(String size, String type, double cost) {
        this.size = size;
        this.type = type;
        this.cost = cost;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
}
