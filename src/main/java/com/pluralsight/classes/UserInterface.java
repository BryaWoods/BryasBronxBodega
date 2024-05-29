package com.pluralsight.classes;

import com.pluralsight.classes.order.NewOrder;
import com.pluralsight.classes.order.Order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UserInterface {

    private Scanner scanner;
    private Map<String, List<String>> menu;
    private List<String> menuItems;

    public UserInterface(){

        scanner = new Scanner(System.in);
        menu = new HashMap<>();
        menuItems = new ArrayList<>();
        try {
            readMenuFromFile("menu.txt");
        } catch (IOException e) {
            System.err.println("Error reading menu file: " + e.getMessage());
        }
        displayHomeScreen();

    }

    private void readMenuFromFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            String currentCategory = "";
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("#")) {
                    currentCategory = line.substring(1).trim();
                    menu.put(currentCategory, new ArrayList<>());
                } else if (!line.isEmpty()) {
                    menu.get(currentCategory).add(line);
                    menuItems.add(line);
                }
            }
        }
    }

    private void displayCategoryMenu(String category) {
        List<String> items = menu.get(category);
        if (items != null) {
            System.out.println(category + ":");
            for (String item : items) {
                System.out.println("  - " + item);
            }
        }
    }

    public void displayHomeScreen() {

        while (true) {
            System.out.println("Welcome Brya's Bronx Bodega!");
            System.out.println("");
            System.out.println("1) New Order");
            System.out.println("0) Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    customerOrder();
                    break;
                case 0:
                    System.out.println("Thank you for visiting DELI-cious. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    public void customerOrder() {

        Order order = new NewOrder();

        while (true) {
            System.out.println("New Order - Please choose an option:");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addSandwich(order);
                    break;
                case 2:
                    addDrink(order);
                    break;
                case 3:
                    addChips(order);
                    break;
                case 4:
                    checkout(order);
                    return;
                case 0:
                    cancelOrder(order);
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }

    public void addSandwich(Order order){

    }

    public void addDrink(Order order) {

    }

    public void addChips(Order order) {

    }
    public void checkout(Order order){

    }

    public void cancelOrder(Order order){

    }

}
