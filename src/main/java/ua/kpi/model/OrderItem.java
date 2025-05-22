package ua.kpi.model;

public class OrderItem {
    private int id;
    private int orderId;
    private int pizzaId;
    private int quantity;

    
    public OrderItem() {}

    public OrderItem(int id, int orderId, int pizzaId, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.pizzaId = pizzaId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
               "id=" + id +
               ", orderId=" + orderId +
               ", pizzaId=" + pizzaId +
               ", quantity=" + quantity +
               '}';
    }
} 