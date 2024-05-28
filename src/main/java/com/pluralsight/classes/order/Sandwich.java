package com.pluralsight.classes.order;

import com.pluralsight.classes.topping.Topping;

import java.util.List;

public class Sandwich {

    private int size;
    private String breadType;
    private List<Topping> toppings;
    private boolean isToasted;

    public Sandwich(int size, String breadType, List<Topping> toppings, boolean isToasted) {
        this.size = size;
        this.breadType = breadType;
        this.toppings = toppings;
        this.isToasted = isToasted;
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

    public List<Topping> getToppings() {
        return toppings;
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

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    public double calculateSandwichCost() {
        double cost = 0.0;
        for (Topping topping : toppings) {
            cost += topping.getCost();
        }
        return cost;
    }
}
