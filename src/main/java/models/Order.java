package models;

public class Order {

    private int id;
    private Customer customer;
    private Order order;

    public Order(int id, Customer customer, Order order) {
        this.id = id;
        this.customer = customer;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
