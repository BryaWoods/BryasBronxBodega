package com.pluralsight.classes.order;

import com.pluralsight.classes.files.ReceiptFileManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private static int nextOrderId = 1;
    private int orderId;
    private int lastAddedSandwichIndex;
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chip> chips;
    private List<Sides> sides;
    private LocalDateTime timeOfOrder;


    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
        this.timeOfOrder = LocalDateTime.now();
        this.lastAddedSandwichIndex = -1;
        this.orderId = nextOrderId++;
        this.sides = new ArrayList<>();
    }

    public List<Sides> getSides() {
        return sides;
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }


    public List<Drink> getDrinks() {
        return drinks;
    }


    public List<Chip> getChips() {
        return chips;
    }



    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);

    }


    public void addDrink(Drink drink) {
        drinks.add(drink);

    }


    public void addChips(Chip chip) {
        chips.add(chip);

    }


    public void addSides(Sides side) {
        sides.add(side);
    }



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
        for (Sides side : sides) {
            total += side.getCost();
        }
        return total;
    }


    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Order Details:\n");
        builder.append("Time of Order: ").append(timeOfOrder).append("\n");

        builder.append("Sandwiches:\n");
        for (Sandwich sandwich : sandwiches) {
            builder.append(sandwich.toString()).append("\n");
        }

        builder.append("Drinks:\n");
        for (Drink drink : drinks) {
            builder.append(drink.toString()).append("\n");
        }

        builder.append("Chips:\n");
        for (Chip chip : chips) {
            builder.append(chip.toString()).append("\n");
        }

        builder.append("Sides:\n");
        for (Sides side : sides) {
            builder.append(side.toString()).append("\n");
        }

        return builder.toString();
    }


    public void createOrderReceipt() {
        String orderDetails = toString();
        double totalCost = calculateOrderTotal();

        ReceiptFileManager receiptFileManager = new ReceiptFileManager();

        receiptFileManager.createReceiptFile(orderDetails, totalCost);


    }


    public void getAllSandwichDetails() {

        System.out.println("Sandwich Details:");
        for (int i = 0; i < sandwiches.size(); i++) {
            System.out.println("Sandwich " + (i + 1) + ":");
            System.out.println(sandwiches.get(i).getDetails());
            System.out.println("----------------------");

        }
    }




    public void removeMostRecentSandwich() {
        if (lastAddedSandwichIndex != -1) {
            sandwiches.remove(lastAddedSandwichIndex);
            lastAddedSandwichIndex = -1;
            System.out.println("Most recent sandwich removed from order.");
        } else {
            System.out.println("No sandwiches to remove.");
        }
    }

    public void getCurrentOrder() {
        System.out.println("Current Order:");

        if (sandwiches.isEmpty() && drinks.isEmpty() && chips.isEmpty() && sides.isEmpty()) {
            System.out.println("Your order is empty.");
        } else {
            if (!sandwiches.isEmpty()) {
                System.out.println("Sandwiches:");
                for (Sandwich sandwich : sandwiches) {
                    System.out.println(sandwich.getDetails());
                    System.out.println("----------------------");
                }
            }
            if (!drinks.isEmpty()) {
                System.out.println("Drinks:");
                for (Drink drink : drinks) {
                    System.out.println(drink.getDetails());
                    System.out.println("----------------------");
                }
            }
            if (!chips.isEmpty()) {
                System.out.println("Chips:");
                for (Chip chip : chips) {
                    System.out.println(chip.getDetails());
                    System.out.println("----------------------");
                }
            }
            if (!sides.isEmpty()) {
                System.out.println("Sides:");
                for (Sides side : sides) {
                    System.out.println(side.getDetails());
                    System.out.println("----------------------");
                }
            }

            System.out.println("Total Cost: $" + calculateOrderTotal());
        }
    }


    public void removeDrink(Drink drink) {
        drinks.remove(drink);
    }

    public void removeChip(Chip chip) {
        chips.remove(chip);
    }

    public void removeSides(Sides side) {
        sides.remove(side);
    }


    public int getOrderId() {
        return orderId;
    }




}