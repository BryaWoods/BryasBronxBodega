package com.pluralsight.classes;

import com.pluralsight.classes.files.MenuFileManager;
import com.pluralsight.classes.order.*;
import com.pluralsight.classes.topping.PremiumTopping;
import com.pluralsight.classes.topping.RegularTopping;
import com.pluralsight.classes.order.Sides;

import java.io.IOException;
import java.util.*;


public class UserInterface {

    private Scanner scanner;
    private MenuFileManager menuFileManager;
    private OrdersManager ordersManager;
    Order order = new Order();

    public UserInterface(){

        scanner = new Scanner(System.in);
        menuFileManager = new MenuFileManager();
        ordersManager= new OrdersManager();
        try {
            menuFileManager.readMenuFromFile("menu.txt");
        } catch (IOException e) {
            System.err.println("Error reading menu file: " + e.getMessage());
        }
        displayHomeScreen();

    }

    public void displayCategoryMenu(String category) {
        List<String> items = menuFileManager.getMenuItems(category);
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
    }

    public void displayHomeScreen() {

        while (true) {
            System.out.println(" ");
            System.out.println("🅆🄴🄻🄲🄾🄼🄴 ② 🄱🅁🅈🄰'🅂 🄱🅁🄾🄽🅇 🄱🄾🄳🄴🄶🄰");
            System.out.println("Enter your name to proceed:");
            String name = scanner.nextLine();

            if (name.equalsIgnoreCase("Staff")) {


                if (authenticatePassword()) {
                    System.out.println("Authentication successful.");
                    displayEmployeeMenu();
                } else {
                    System.out.println("Authentication failed. Access denied.");
                    return;
                }
            } else {
                System.out.println("Hola, " + name + "! Let's start your order.");
                customerOrder();
            }
        }
    }

    private void displayEmployeeMenu() {
        while (true) {
            System.out.println("\nEmployee Menu:");
            System.out.println("1. Complete Orders");
            System.out.println("2. Display Orders in Progress");
            System.out.println("3. Display Completed Orders");
            System.out.println("0. Exit Employee Menu");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        completeOrdersMenu();
                        break;
                    case 2:
                        displayOrdersInProgress();
                        break;
                    case 3:
                        displayCompletedOrders();
                        break;
                    case 0:
                        System.out.println("Exiting Employee Menu.");
                        clearCurrentOrder();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }


        public void customerOrder() {


        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Add Side");
            System.out.println("5) Secret Menu");
            System.out.println("6) Checkout");
            System.out.println("0) Cancel Order");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addSandwich();
                    break;
                case 2:
                    addDrink();
                    break;
                case 3:
                    addChips();
                    break;
                case 4:
                    addSidesToOrder();
                    break;
                case 5:
                    displaySecretMenu();
                    break;
                case 6:
                    checkout();
                    return;
                case 0:
                    cancelOrder();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }

    public void addSandwich() {
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

        System.out.println("Select your bread type:");
        displayCategoryMenu("Breads");

        String breadChoice = scanner.nextLine();

        try {
            int breadNumber = Integer.parseInt(breadChoice);
            List<String> breads = menuFileManager.getMenuItems("Breads");
            if (breadNumber > 0 && breadNumber <= breads.size()) {
                String breadType = breads.get(breadNumber - 1);
                sandwich.setBreadType(breadType);
                System.out.println(breadType + " selected.");
            } else {
                System.out.println("Invalid choice. Please try again.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }


        //MEAT


        while (true) {
            System.out.println("Select a meat topping (or type 'done' to finish):");
            displayCategoryMenu("Meats");

            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            try {
                int meatNumber = Integer.parseInt(input);
                List<String> meats = menuFileManager.getMenuItems("Meats");
                if (meatNumber > 0 && meatNumber <= meats.size()) {
                    String meat = meats.get(meatNumber - 1);
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
                    System.out.println(meat + " added.");

                    System.out.println("Would you like to double your meat? (yes/no)");
                    String extraMeat = scanner.nextLine();
                    if (extraMeat.equalsIgnoreCase("yes")) {
                        double doubleExtraCost = 0.0;
                        switch (sandwich.getSize()) {
                            case 4:
                                doubleExtraCost = 0.50;
                                break;
                            case 8:
                                doubleExtraCost = 1.00;
                                break;
                            case 12:
                                doubleExtraCost = 1.50;
                                break;
                        }
                        sandwich.addTopping(new PremiumTopping("Extra " + meat, doubleExtraCost));
                        sandwich.setCost(sandwich.getCost() + doubleExtraCost);
                        System.out.println("Extra " + meat + " added.");
                    }
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }



        //CHEESE

        while (true) {
            System.out.println("Select a cheese topping (or type 'done' to finish):");
            displayCategoryMenu("Cheeses");

            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            try {
                int cheeseNumber = Integer.parseInt(input);
                List<String> cheeses = menuFileManager.getMenuItems("Cheeses");
                if (cheeseNumber > 0 && cheeseNumber <= cheeses.size()) {
                    String cheese = cheeses.get(cheeseNumber - 1);
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
                    System.out.println(cheese + " added.");

                    System.out.println("Would you like to double your cheese? (yes/no)");
                    String extraCheese = scanner.nextLine();
                    if (extraCheese.equalsIgnoreCase("yes")) {
                        double doubleExtraCost = 0.0;
                        switch (sandwich.getSize()) {
                            case 4:
                                doubleExtraCost = 0.30;
                                break;
                            case 8:
                                doubleExtraCost = 0.60;
                                break;
                            case 12:
                                doubleExtraCost = 0.90;
                                break;
                        }
                        sandwich.addTopping(new PremiumTopping("Extra " + cheese, doubleExtraCost));
                        sandwich.setCost(sandwich.getCost() + doubleExtraCost);
                        System.out.println("Extra " + cheese + " added.");
                    }
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }


        //TOPPINGS


        int maxToppings = 5;
        int toppingCount = 0;

        while (true) {
            if (toppingCount >= maxToppings) {
                System.out.println("You have reached the maximum limit of toppings.");
                break;
            }

            System.out.println("Select a regular topping (or type 'done' to finish):");
            System.out.println("Limit of 5 toppings");
            displayCategoryMenu("Regular Toppings");

            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            try {
                int toppingNumber = Integer.parseInt(input);
                List<String> toppings = menuFileManager.getMenuItems("Regular Toppings");
                if (toppingNumber > 0 && toppingNumber <= toppings.size()) {
                    String topping = toppings.get(toppingNumber - 1);
                    sandwich.addTopping(new RegularTopping(topping));
                    System.out.println(topping + " added.");
                    toppingCount++;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        //CONDIMENTS

        while (true) {
            System.out.println("Select a condiment (or type 'done' to finish):");
            displayCategoryMenu("Condiments");

            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            try {
                int condimentNumber = Integer.parseInt(input);
                List<String> condiments = menuFileManager.getMenuItems("Condiments");
                if (condimentNumber > 0 && condimentNumber <= condiments.size()) {
                    String condiment = condiments.get(condimentNumber - 1);
                    sandwich.addTopping(new RegularTopping(condiment));
                    System.out.println(condiment + " added.");
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
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


        order.addSandwich(sandwich);

        order.getAllSandwichDetails();

        System.out.println("Does everything look correct?");

        System.out.println("yes/no)");
        String confirmChoice = scanner.nextLine();
        if (confirmChoice.equalsIgnoreCase("yes")) {
            System.out.println("Sandwich added to your order.");
        } else {
            order.removeMostRecentSandwich();
            System.out.println("Sandwich not added.");
        }


    }



    public void addDrink() {

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



    public void addChips() {

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

    public void addSidesToOrder() {

        System.out.println("Select a side (or type 'done' to finish):");
        displayCategoryMenu("Sides");

        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("done")) {
            return;
        }

        try {
            int sideNumber = Integer.parseInt(input);
            List<String> sides = menuFileManager.getMenuItems("Sides");
            if (sideNumber > 0 && sideNumber <= sides.size()) {
                String side = sides.get(sideNumber - 1);
                double cost = 0.0; //
                if (sideNumber == 3) {
                    cost = 15.0;
                }
                order.addSides(new Sides(side, cost));
                System.out.println(side + " added to your order.");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }



    public void checkout() {
        displayConfirmationScreen();
        ordersManager.addOrder(order);
        order = new Order();

        System.out.println("Estimated wait time 15 min");

    }


    public void editOrder() {


        while (true) {
            System.out.println("Edit Order:");
            System.out.println("1) Remove Sandwich");
            System.out.println("2) Remove Drink");
            System.out.println("3) Remove Chip");
            System.out.println("4) Remove Side");
            System.out.println("5) Done Editing");

            int choice = scanner.nextInt();
            scanner.nextLine();

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
                    removeSideFromOrder();
                    break;
                case 5:
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void removeSandwichFromOrder() {



        if (order == null || order.getSandwiches().isEmpty()) {
            System.out.println("No sandwiches to remove.");
            return;
        }

        System.out.println("Select a sandwich to remove:");
        List<Sandwich> sandwiches = order.getSandwiches();
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

    public void removeDrinkFromOrder() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Select the drink you want to remove:");


        List<Drink> drinks = order.getDrinks();
        for (int i = 0; i < drinks.size(); i++) {
            System.out.println((i + 1) + ") " + drinks.get(i).getType());
        }


        int choice = scanner.nextInt();
        scanner.nextLine();


        if (choice >= 1 && choice <= drinks.size()) {
            Drink drinkToRemove = drinks.get(choice - 1);
            order.removeDrink(drinkToRemove);
            System.out.println(drinkToRemove.getType() + " removed from the order.");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    public void removeChipFromOrder() {


        Scanner scanner = new Scanner(System.in);

        System.out.println("Select the chips you want to remove:");


        List<Chip> chips = order.getChips();
        for (int i = 0; i < chips.size(); i++) {
            System.out.println((i + 1) + ") " + chips.get(i).getType());
        }


        int choice = scanner.nextInt();
        scanner.nextLine();


        if (choice >= 1 && choice <= chips.size()) {
            Chip chipToRemove = chips.get(choice - 1);
            order.removeChip(chipToRemove);
            System.out.println(chipToRemove.getType() + " removed from the order.");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    public void removeSideFromOrder() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select the side you want to remove:");

        List<Sides> sides = order.getSides();
        for (int i = 0; i < sides.size(); i++) {
            System.out.println((i + 1) + ") " + sides.get(i).getName());
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice >= 1 && choice <= sides.size()) {
            Sides sideToRemove = sides.get(choice - 1);
            order.removeSides(sideToRemove);
            System.out.println(sideToRemove.getName() + " removed from the order.");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    public void displayConfirmationScreen() {
        while(true) {
            System.out.println("Your current order:");
            displayOrderDetails();

            System.out.println("\nWould you like to edit your order?");
            System.out.println("1) Edit order");
            System.out.println("2) Confirm order");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    editOrder();
                    break;
                case 2:
                    confirmOrder();
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    private void displayOrderDetails() {
    order.getCurrentOrder();


    }

    public void confirmOrder() {

        System.out.println("\nOrder confirmed!");
        System.out.println("Generating receipt...");
        order.createOrderReceipt();
        System.out.println("Gracias por comprar en Brya's Bronx Bodega!");

    }

    public void clearCurrentOrder(){

        order.getSandwiches().clear();
        order.getDrinks().clear();
        order.getChips().clear();


    }

    private void completeOrdersMenu() {

        displayOrdersInProgress();


        System.out.println("Enter the ID of the order to complete:");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        Order orderToComplete = null;
        List<Order> pendingOrdersList = ordersManager.getPendingOrders();
        for (Order order : pendingOrdersList ) {
            if (order.getOrderId() == orderId) {
                orderToComplete = order;
                break;
            }
        }

        if (orderToComplete != null) {
            ordersManager.completeOrder(orderToComplete);
            System.out.println("Order completed and moved to completed orders list.");
        } else {
            System.out.println("Order not found. Please enter a valid ID.");
        }
    }





    private void displayOrdersInProgress() {


        System.out.println("Orders in Progress:");
        List<Order> pendingOrdersList = ordersManager.getPendingOrders();
        for (Order order : pendingOrdersList) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Order Status: In Progress");
            System.out.println("Order Details:");
            String orderDetails = order.toString();
            System.out.println(orderDetails);
            System.out.println("--------------------------------------");
        }
    }


    private void displayCompletedOrders() {

        ordersManager.displayArchiveOrders();

    }

    private boolean authenticatePassword() {
        System.out.println("Enter password to access employee functions:");
        String inputPassword = scanner.nextLine();

        String correctPassword = "BadBunny";

        if (inputPassword.equals(correctPassword)) {
            System.out.println("Access granted.");
            return true;
        } else {
            System.out.println("Incorrect password. Access denied.");
            return false;
        }
    }






    public void cancelOrder() {
        ordersManager.removeOrder(order);
    }

    private void displaySecretMenu() {
        SecretMenu secretMenu = new SecretMenu(order);
        System.out.println("Welcome to Brya's Secret Menu!");
        System.out.println("Choose from our wild and exclusive offerings:");

        System.out.println("1) Hail Brya - $7.00 - Steak, American, Lettuce, Special Sauce");
        System.out.println("2) Beach Don't Kill My Vibe - $6.50 - Piña Colada, Limon lays, Shot of tequila");
        System.out.println("3) PB&J - $4.00 - Peanut butter, Grape jelly ");
        System.out.println("4) WIITB?! - $6.50 - Ham, Cheese, Mayo, Lays on the sandwich");
        System.out.println("5) I ♥ NYC - $7.00 - Bagel, Cream Cheese");
        System.out.println("6) Bad Bunny Burger  - $6.50 - Grilled Cheese with a burger inside");


        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                secretMenu.addHailBrya();
                break;
            case 2:
                secretMenu.addBeachDontKillMyVibe();
                break;
            case 3:
                secretMenu.addPBJ();
                break;
            case 4:
                secretMenu.addWIITB();
                break;
            case 5:
                secretMenu.addILoveNYC();
                break;
            case 6:
                secretMenu.addBadBunnyBurger();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }


}
