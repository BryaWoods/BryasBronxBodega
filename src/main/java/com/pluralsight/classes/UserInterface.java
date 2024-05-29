package com.pluralsight.classes;

import com.pluralsight.classes.order.Drink;
import com.pluralsight.classes.order.NewOrder;
import com.pluralsight.classes.order.Order;
import com.pluralsight.classes.order.Sandwich;
import com.pluralsight.classes.topping.PremiumTopping;
import com.pluralsight.classes.topping.RegularTopping;

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
            System.out.println(" ");
            System.out.println("Welcome 2 Brya's Bronx Bodega!");
            System.out.println("Press 1 to start your order bby");
            System.out.println("1) New Order");
            System.out.println("0) Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    customerOrder();
                    break;
                case 0:
                    System.out.println("Thank you for visiting Brya's Bronx Bodega. Peace & Luv!");
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

    public void addSandwich(Order order) {
        Sandwich sandwich = new Sandwich();

        System.out.println("Choose a size:");
        System.out.println("1) 4\" Sandwich");
        System.out.println("2) 8\" Sandwich");
        System.out.println("3) 12\" Sandwich");

        int sizeChoice = scanner.nextInt();
        scanner.nextLine();

        switch (sizeChoice) {
            case 1:
                sandwich.setSize(4);
                sandwich.setCost(5.50);
                break;
            case 2:
                sandwich.setSize(8);
                sandwich.setCost(7.00);
                break;
            case 3:
                sandwich.setSize(12);
                sandwich.setCost(8.50);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        //BREAD

        System.out.println("Select your bread type or choose a lettuce wrap:");
        displayCategoryMenu("Breads");

        String breadType = scanner.nextLine();

        if (breadType.equalsIgnoreCase("Lettuce Wrap")) {
            sandwich.setBreadType("Lettuce Wrap");
        } else {
            boolean validBread = false;

            for (String item : menu.get("Breads")) {
                if (item.equalsIgnoreCase(breadType)) {
                    sandwich.setBreadType(breadType);
                    validBread = true;
                    break;
                }
            }

            if (!validBread) {
                System.out.println("Invalid choice. Please try again.");
                return;


            }
        }


        //MEAT


        while (true) {
            System.out.println("Select a meat topping (or type 'done' to finish):");
            displayCategoryMenu("Meats");

            String meat = scanner.nextLine();
            if (meat.equalsIgnoreCase("done")) {
                break;
            }


            boolean validMeat = false;
            for (String item : menu.get("Meats")) {
                if (item.startsWith(meat)) {
                    double extraCost = 0.0;
                    switch (sandwich.getSize()) {
                        case 4:
                            extraCost = 1.00;
                            break;
                        case 8:
                            extraCost = 2.00;
                            break;
                        case 12:
                            extraCost = 3.00;
                            break;
                    }
                    sandwich.addTopping(new PremiumTopping(meat, extraCost));
                    sandwich.setCost(sandwich.getCost() + extraCost);
                    validMeat = true;
                    break;
                }
            }
            if (!validMeat) {
                System.out.println("Invalid choice. Please try again.");
            } else {
                System.out.println("Would you like to double your meat? (yes/no)");
                String extraMeat = scanner.nextLine();
                if (extraMeat.equalsIgnoreCase("yes")) {
                    double extraCost = 0.0;
                    switch (sandwich.getSize()) {
                        case 4:
                            extraCost = .50;
                            break;
                        case 8:
                            extraCost = 1.00;
                            break;
                        case 12:
                            extraCost = 1.50;
                            break;
                    }
                    sandwich.addTopping(new PremiumTopping("Extra " + meat, extraCost));
                    sandwich.setCost(sandwich.getCost() + extraCost);
                }
            }
        }


        //CHEESE

        while (true) {
            System.out.println("Select a cheese topping (or type 'done' to finish):");
            displayCategoryMenu("Cheeses");

            String cheese = scanner.nextLine();
            if (cheese.equalsIgnoreCase("done")) {
                break;
            }

            boolean validCheese = false;
            for (String item : menu.get("Cheeses")) {
                if (item.startsWith(cheese)) {
                    double extraCost = 0.0;
                    switch (sandwich.getSize()) {
                        case 4:
                            extraCost = 0.75;
                            break;
                        case 8:
                            extraCost = 1.00;
                            break;
                        case 12:
                            extraCost = 1.50;
                            break;
                    }
                    sandwich.addTopping(new PremiumTopping(cheese, extraCost));
                    sandwich.setCost(sandwich.getCost() + extraCost);
                    validCheese = true;
                    break;
                }
            }
            if (!validCheese) {
                System.out.println("Invalid choice. Please try again.");

            } else {

                System.out.println("Would you like to double yor cheese?!? (yes/no)");
                String extraCheese = scanner.nextLine();
                if (extraCheese.equalsIgnoreCase("yes")) {
                    double extraCost = 0.0;
                    switch (sandwich.getSize()) {
                        case 4:
                            extraCost = 0.30;
                            break;
                        case 8:
                            extraCost = .60;
                            break;
                        case 12:
                            extraCost = 0.90;
                            break;
                    }
                    sandwich.addTopping(new PremiumTopping("Extra " + cheese, extraCost));
                    sandwich.setCost(sandwich.getCost() + extraCost);
                }
            }
        }


        //TOPPINGS


        while (true) {
            System.out.println("Select a regular topping (or type 'done' to finish):");
            displayCategoryMenu("Regular Toppings");

            String topping = scanner.nextLine();
            if (topping.equalsIgnoreCase("done")) {
                break;
            }

            boolean validTopping = false;
            for (String item : menu.get("Regular Toppings")) {
                if (item.equalsIgnoreCase(topping)) {
                    sandwich.addTopping(new RegularTopping(topping));
                    validTopping = true;
                    break;
                }
            }
            if (!validTopping) {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        //CONDIMENTS

        while (true) {
            System.out.println("Select a condiment (or type 'done' to finish):");
            displayCategoryMenu("Condiments");

            String condiment = scanner.nextLine();
            if (condiment.equalsIgnoreCase("done")) {
                break;
            }

            boolean validCondiment = false;
            for (String item : menu.get("Condiments")) {
                if (item.equalsIgnoreCase(condiment)) {
                    sandwich.addTopping(new RegularTopping(condiment));
                    validCondiment = true;
                    break;
                }
            }
            if (!validCondiment) {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        //TOASTED

        if (!sandwich.getBreadType().equalsIgnoreCase("Lettuce Wrap")) {
            System.out.println("Would you like the sandwich toasted? (yes/no)");
            String toasted = scanner.nextLine();
            sandwich.setToasted(toasted.equalsIgnoreCase("yes"));
        } else {
            sandwich.setToasted(false);
        }

        System.out.println("Your sandwich:");
        System.out.println("Size: " + sandwich.getSize() + "\"");
        System.out.println("Bread: " + sandwich.getBreadType());
        System.out.println("Toppings: " + sandwich.getToppings());
        System.out.println("Sauces: " + sandwich.getCondiments());
        System.out.println("Toasted: " + (sandwich.isToasted() ? "Yes" : "No"));
        System.out.println("Cost: $" + sandwich.getCost());

        order.addSandwich(sandwich);
        System.out.println("Sandwich added to order!");

        System.out.println("Confirm this sandwich? (yes/no)");
        String confirmChoice = scanner.nextLine();
        if (confirmChoice.equalsIgnoreCase("yes")) {
            order.addSandwich(sandwich);
            System.out.println("Sandwich added to your order.");
        } else {
            System.out.println("Sandwich not added.");
        }


    }



    public void addDrink(Order order) {

        System.out.println("Choose drink size:");
        System.out.println("1) Small");
        System.out.println("2) Medium");
        System.out.println("3) Large");

        int sizeChoice = scanner.nextInt();
        scanner.nextLine();

        String size = null;
        double cost = 0.0;


        switch (sizeChoice) {
            case 1:
                size = "Small";
                cost = 2.00;
                break;
            case 2:
                size = "Medium";
                cost = 2.50;
                break;
            case 3:
                size = "Large";
                cost = 3.00;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        System.out.println("Enter drink choice: ");
        String type = scanner.nextLine();

        Drink drink = new Drink(size, type, cost);
        order.addDrink(drink);
        System.out.println(type + " (" + size + ") added to order. Cost: $" + cost);



    }

    public void addChips(Order order) {

    }
    public void checkout(Order order){

    }

    public void cancelOrder(Order order){

    }

}
