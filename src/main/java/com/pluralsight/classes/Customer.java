package com.pluralsight.classes;

import com.pluralsight.classes.order.Order;

public class Customer {

    private String name;
    private Order order;

    public Customer(String name) {
        this.name = name;
    }

    public Order getCurrentOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
