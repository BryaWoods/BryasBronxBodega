package com.pluralsight.classes.order;

import com.pluralsight.classes.topping.Topping;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {

    private int size;
    private String breadType;
    private List<Topping> toppings;
    private String condiments;
    private boolean isToasted;
    private double cost;

    public Sandwich() {
        this.toppings = new ArrayList<>();
        this.isToasted = false;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getBreadType() {
        return breadType;
    }

    public void setBreadType(String breadType) {
        this.breadType = breadType;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    public String getCondiments() {
        return condiments;
    }

    public void setCondiments(String condiments) {
        this.condiments = condiments;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    public double calculateSandwichCost() {
        double totalCost = this.cost;
        for (Topping topping : toppings) {
            totalCost += topping.getCost();
        }
        return totalCost;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append("\" ").append(breadType).append(" Sandwich\n");
        if (isToasted) {
            sb.append("Toasted\n");
        }
        sb.append("Toppings:\n");
        for (Topping topping : toppings) {
            sb.append(" - ").append(topping.getName()).append("\n");
        }
        sb.append("Total Cost: $").append(String.format("%.2f", calculateSandwichCost()));
        return sb.toString();
    }
    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append("\" ").append(breadType).append(" Sandwich\n");
        if (isToasted) {
            sb.append("Toasted\n");
        }
        sb.append("Toppings:\n");
        for (Topping topping : toppings) {
            sb.append(" - ").append(topping.getName()).append("\n");
        }
        sb.append("Total Cost: $").append(String.format("%.2f", calculateSandwichCost()));
        return sb.toString();
    }

}
