package ua.kpi.model;

import java.util.List;
import java.util.ArrayList;

public class Order {
    private int id;
    private int customerId;
    private List<OrderItem> items;

    
    public Order() {
        this.items = new ArrayList<>();
    }

    public Order(int id, int customerId) {
        this.id = id;
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    public Order(int id, int customerId, List<OrderItem> items) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void addItem(OrderItem item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }

    @Override
    public String toString() {
        return "Order{" +
               "id=" + id +
               ", customerId=" + customerId +
               ", items=" + items +
               '}';
    }
} 