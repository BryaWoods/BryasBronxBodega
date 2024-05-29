package com.pluralsight.classes.order;

import com.pluralsight.classes.Order;

import java.util.ArrayList;
import java.util.List;

public class OrdersManager {
    private List<Order> orders;

    public OrdersManager() {
        orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public Order getCurrentOrder() {
        if (!orders.isEmpty()) {
            return orders.get(orders.size() - 1);
        }
        return null;
    }

    public String getOrderDetails(Order order) {
        if (orders.contains(order)) {
            return order.toString();
        }
        return "Order not found.";
    }

    public void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders currently.");
        } else {
            System.out.println("Orders:");
            for (Order order : orders) {
                System.out.println(order);
            }


        }
    }
}
