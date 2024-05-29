package com.pluralsight.classes;

import com.pluralsight.classes.files.MenuFileManager;
import com.pluralsight.classes.order.*;
import com.pluralsight.classes.topping.PremiumTopping;
import com.pluralsight.classes.topping.RegularTopping;
import java.io.IOException;
import java.util.*;


public class UserInterface {

    private Scanner scanner;
    private MenuFileManager menuFileManager;
    private Map<String, List<String>> menu;
    private OrdersManager ordersManager;

    public UserInterface(){

        scanner = new Scanner(System.in);
        menuFileManager = new MenuFileManager();
        menu = new HashMap<>();
        ordersManager = new OrdersManager();
        try {
            menuFileManager.readMenuFromFile("menu.txt");
        } catch (IOException e) {
            System.err.println("Error reading menu file: " + e.getMessage());
        }
        displayHomeScreen();

    }

    private void readMenuFromFile(String filename) throws IOException {
        menuFileManager.readMenuFromFile("menu.txt");
    }

    private void displayCategoryMenu(String category) {
        List<String> items = menuFileManager.getMenuItems(category);
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

        Order order = new Order();

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

        order.getAllSandwichDetails();

        System.out.println("Does everything look correct?");

        System.out.println("yes/no)");
        String confirmChoice = scanner.nextLine();
        if (confirmChoice.equalsIgnoreCase("yes")) {
            System.out.println("Sandwich added to your order.");
        } else {
            order.removeSandwich();
            System.out.println("Sandwich not added.");
        }


    }



    public void addDrink(Order order) {

        System.out.println("Select drink size:");
        System.out.println("1) Small Drink - $2.00");
        System.out.println("2) Medium Drink - $2.50");
        System.out.println("3) Large Drink - $3.00");

        int sizeChoice = scanner.nextInt();
        scanner.nextLine();

        String size;
        double cost;

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


        System.out.println("Select a drink for " + size + ":");
        System.out.println("1) Coca-Cola");
        System.out.println("2) Diet Coke");
        System.out.println("3) Coke Zero Sugar");
        System.out.println("4) Sprite");
        System.out.println("5) Fanta");
        System.out.println("6) Barq's Root Beer");
        System.out.println("7) Hi-C");
        System.out.println("8) Minute Maid Lemonade");
        System.out.println("9) Coco Rico Soda");
        System.out.println("10) Kola Champagne");
        System.out.println("11) Medalla Beer");
        System.out.println("12) Piña Colada");
        System.out.println("13) Horchata");
        System.out.println("14) Aguas Frescas");

        int drinkChoice = scanner.nextInt();
        scanner.nextLine();

        String drinkName;

        switch (drinkChoice) {
            case 1:
                drinkName = "Coca-Cola";
                break;
            case 2:
                drinkName = "Diet Coke";
                break;
            case 3:
                drinkName = "Coke Zero Sugar";
                break;
            case 4:
                drinkName = "Sprite";
                break;
            case 5:
                drinkName = "Fanta";
                break;
            case 6:
                drinkName = "Barq's Root Beer";
                break;
            case 7:
                drinkName = "Hi-C";
                break;
            case 8:
                drinkName = "Minute Maid Lemonade";
                break;
            case 9:
                drinkName = "Coco Rico Soda";
                break;
            case 10:
                drinkName = "Kola Champagne";
                break;
            case 11:
                drinkName = "Medalla Beer";
                break;
            case 12:
                drinkName = "Piña Colada";
                break;
            case 13:
                drinkName = "Horchata";
                break;
            case 14:
                drinkName = "Aguas Frescas";
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        Drink drink = new Drink(size, drinkName, cost);
        order.addDrink(drink);

        System.out.println(drinkName + " (" + size + ") added to your order.");
    }



    public void addChips(Order order) {

        System.out.println("Select a chip type:");
        System.out.println("1) Original Lays");
        System.out.println("2) BBQ Lays");
        System.out.println("3) Limon Lays");
        System.out.println("4) Cheetos");
        System.out.println("5) Hot Cheetos");
        System.out.println("6) Doritos");
        System.out.println("7) Cool Ranch Doritos");
        System.out.println("8) Chicharrones");
        System.out.println("9) Original Plantain Chips");
        System.out.println("10) Chile Limon Plantain Chips");

        int chipChoice = scanner.nextInt();
        scanner.nextLine();


        String chipName;
        double cost = 1.50;

        switch (chipChoice) {
            case 1:
                chipName = "Original Lays";
                break;
            case 2:
                chipName = "BBQ Lays";
                break;
            case 3:
                chipName = "Limon Lays";
                break;
            case 4:
                chipName = "Cheetos";
                break;
            case 5:
                chipName = "Hot Cheetos";
                break;
            case 6:
                chipName = "Doritos";
                break;
            case 7:
                chipName = "Cool Ranch Doritos";
                break;
            case 8:
                chipName = "Chicharrones";
                break;
            case 9:
                chipName = "Original Plantain Chips";
                break;
            case 10:
                chipName = "Chile Limon Plantain Chips";
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        Chip chip = new Chip(chipName, cost);
        order.addChips(chip);

        System.out.println(chipName + " added to your order.");



    }


    public void checkout(Order order){

        System.out.println("Your Order:");
        System.out.println(order);

        System.out.println("Total Cost: $" + order.calculateOrderTotal());
        System.out.println("Gracias por comprar en Brya's Bronx Bodega!");

        order.createOrderReceipt();

    }

    public void addOrder(Order order) {
        ordersManager.addOrder(order);
    }

    public void editOrder() {
        boolean editing = true;

        while (editing) {
            System.out.println("Edit Order:");
            System.out.println("1) Remove Sandwich");
            System.out.println("2) Remove Drink");
            System.out.println("3) Remove Chip");
            System.out.println("4) Done Editing");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    removeSandwichFromOrder();
                    break;
                case 2:
                    removeDrinkFromOrder();
                    break;
                case 3:
                    removeChipFromOrder();
                    break;
                case 4:
                    editing = false; // Exit editing loop
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void removeSandwichFromOrder() {
        Order currentOrder = ordersManager.getCurrentOrder(); // Assuming you have a method to get the current order

        if (currentOrder == null || currentOrder.getSandwiches().isEmpty()) {
            System.out.println("No sandwiches to remove.");
            return;
        }

        System.out.println("Select a sandwich to remove:");
        List<Sandwich> sandwiches = currentOrder.getSandwiches();
        for (int i = 0; i < sandwiches.size(); i++) {
            System.out.println((i + 1) + ") " + sandwiches.get(i).getDetails());
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > sandwiches.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Sandwich removedSandwich = sandwiches.remove(choice - 1);
        System.out.println("Removed: " + removedSandwich.getDetails());
    }



    public void cancelOrder(Order order) {
        ordersManager.removeOrder(order);
    }



}
