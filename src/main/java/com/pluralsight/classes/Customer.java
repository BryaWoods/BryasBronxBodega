package com.pluralsight.classes;

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
