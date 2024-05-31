package com.pluralsight.classes.order;

import com.pluralsight.classes.topping.RegularTopping;

public class SecretMenu {

    private Order order;

    public SecretMenu(Order order) {
        this.order = order;
    }

    public void addHailBrya() {
        Sandwich sandwich = new Sandwich();

        sandwich.setSize(8);
        sandwich.setCost(7.00);
        sandwich.setBreadType("white bread");

        sandwich.addTopping(new RegularTopping("Steak"));
        sandwich.addTopping(new RegularTopping("Lettuce"));
        sandwich.addTopping(new RegularTopping("American"));
        sandwich.addTopping(new RegularTopping("Special Sauce"));

        sandwich.setToasted(true);

        order.addSandwich(sandwich);

        System.out.println("Hail Brya added to your order!!");
    }

    public void addBeachDontKillMyVibe() {
        Drink pinaColada = new Drink("Large", "Piña Colada", 6.50);
        Chip limonLays = new Chip("Limon Lays", 0);
        Drink tequilaShot = new Drink("Small", "Tequila Shot", 0);

        order.addDrink(pinaColada);
        order.addChips(limonLays);
        order.addDrink(tequilaShot);

        System.out.println("Beach Don't Kill My Vibe added to your order!!");
    }

    public void addPBJ() {
        Sandwich sandwich = new Sandwich();

        sandwich.setSize(8);
        sandwich.setCost(4.00);
        sandwich.setBreadType("white bread");

        sandwich.addTopping(new RegularTopping("Peanut Butter"));
        sandwich.addTopping(new RegularTopping("Grape Jelly"));

        sandwich.setToasted(false);

        order.addSandwich(sandwich);

        System.out.println("PB&J added to your order!!");
    }

    public void addWIITB() {
        Sandwich sandwich = new Sandwich();

        sandwich.setSize(8);
        sandwich.setCost(6.50);
        sandwich.setBreadType("white bread");

        sandwich.addTopping(new RegularTopping("Ham"));
        sandwich.addTopping(new RegularTopping("Cheese"));
        sandwich.addTopping(new RegularTopping("Mayo"));
        sandwich.addTopping(new RegularTopping("Lays"));

        sandwich.setToasted(true);

        order.addSandwich(sandwich);

        System.out.println("WIITB?! added to your order!!");
    }

    public void addILoveNYC() {
        Sandwich sandwich = new Sandwich();

        sandwich.setSize(8);
        sandwich.setCost(7.00);
        sandwich.setBreadType("bagel");

        sandwich.addTopping(new RegularTopping("Cream Cheese"));

        sandwich.setToasted(true);

        order.addSandwich(sandwich);

        System.out.println("I ♥ NYC added to your order!!");
    }

    public void addBadBunnyBurger() {
        Sandwich sandwich = new Sandwich();

        sandwich.setSize(8);
        sandwich.setCost(6.50);
        sandwich.setBreadType("white bread");

        sandwich.addTopping(new RegularTopping("Grilled Cheese"));
        sandwich.addTopping(new RegularTopping("Burger"));

        sandwich.setToasted(true);

        order.addSandwich(sandwich);

        System.out.println("Bad Bunny Burger added to your order!!");
    }
}

