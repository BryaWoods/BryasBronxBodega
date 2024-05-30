package com.pluralsight.classes.order;

import com.pluralsight.classes.Order;

import java.util.ArrayList;
import java.util.List;

public class OrdersManager {
    private List<Order> orders;
    private List<Order> archiveOrders;
    private List<Order> pendingOrders;

    public OrdersManager() {
        orders = new ArrayList<>();
        archiveOrders = new ArrayList<>();
        pendingOrders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
        pendingOrders.add(order); // Add the order to pending orders when it's added
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        pendingOrders.remove(order); // Also remove from pending orders
    }



    public void completeOrder(Order order) {
        if (pendingOrders.contains(order)) {
            archiveOrders.add(order);
            pendingOrders.remove(order);
            System.out.println("Order completed and archived.");
        } else {
            System.out.println("Order not found in pending orders.");
        }
    }

    public List<Order> getArchiveOrders() {
        return archiveOrders;
    }

    public List<Order> getPendingOrders() {
        return pendingOrders;
    }

    public void displayArchiveOrders() {
        if (archiveOrders.isEmpty()) {
            System.out.println("No completed orders currently.");
        } else {
            System.out.println("Completed Orders:");
            for (Order order : archiveOrders) {
                System.out.println(order);
            }
        }
    }
}



