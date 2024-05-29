package com.pluralsight.classes;

import com.pluralsight.classes.files.ReceiptFileManager;
import com.pluralsight.classes.order.Chip;
import com.pluralsight.classes.order.Drink;
import com.pluralsight.classes.order.Sandwich;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order{


        private List<Sandwich> sandwiches;
        private List<Drink> drinks;
        private List<Chip> chips;
        private LocalDateTime timeOfOrder;
        private Scanner scanner;



        public Order() {
            this.sandwiches = new ArrayList<>();
            this.drinks = new ArrayList<>();
            this.chips = new ArrayList<>();
            this.timeOfOrder = LocalDateTime.now();
            this.scanner = new Scanner(System.in);
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


        public LocalDateTime getTimeOfOrder() {
            return timeOfOrder;
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


        public void removeSandwich(int index) {

            if (index >= 0 && index < sandwiches.size()) {
                sandwiches.remove(index);
                System.out.println("Sandwich removed from order.");
            } else {
                System.out.println("Invalid sandwich index.");
            }
        }

}






