package com.pluralsight.classes.order;

import java.time.LocalDateTime;
import java.util.List;

public interface Order {

    List<Sandwich> getSandwiches();
    List<Drink> getDrinks();
    List<Chip> getChips();
    LocalDateTime getTimeOfOrder();



    void addSandwich(Sandwich sandwich);
    void addDrink(Drink drink);
    void addChips(Chip chip);
    double calculateOrderTotal();
    void createOrderReceipt();
}
