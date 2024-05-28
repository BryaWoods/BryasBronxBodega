package com.pluralsight.classes.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewOrder implements Order {

        private List<Sandwich> sandwiches;
        private List<Drink> drinks;
        private List<Chip> chips;
        private LocalDateTime timeOfOrder;

        public NewOrder() {
            this.sandwiches = new ArrayList<>();
            this.drinks = new ArrayList<>();
            this.chips = new ArrayList<>();
            this.timeOfOrder = LocalDateTime.now();
        }
    @Override
    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    @Override
    public List<Drink> getDrinks() {
        return drinks;
    }

    @Override
    public List<Chip> getChips() {
        return chips;
    }

    @Override
    public LocalDateTime getTimeOfOrder() {
        return timeOfOrder;
    }

    @Override
    public void addSandwich(Sandwich sandwich) {
            sandwiches.add(sandwich);

    }

    @Override
    public void addDrink(Drink drink) {
            drinks.add(drink);

    }

    @Override
    public void addChips(Chip chip) {
            chips.add(chip);

    }

    @Override
    public double calculateOrderTotal() {
        double total = 0.0;
        for (Sandwich sandwich : sandwiches) {
            total += sandwich.calculateSandwichCost();
        }
        for (Drink drink : drinks) {
            total += drink.getCost();
        }
        for (Chip chip : chips) {
            total += chip.getCost();
        }
        return total;
    }

    @Override
    public void createOrderReceipt() {

    }
}
