package com.pluralsight.classes.topping;

public class RegularTopping implements Topping {
    private String name;

    public RegularTopping(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getCost() {
        return 0.0;
    }
}
