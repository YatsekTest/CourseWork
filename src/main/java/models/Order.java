package models;

public class Order {

    private int id;
    private Customer customer;
    private Product product;

    public Order() {
    }

    public Order(Customer customer, Product product) {
        this.customer = customer;
        this.product = product;
    }

    public Order(int id, Customer customer, Product product) {
        this.id = id;
        this.customer = customer;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Order\t{" +
                "id=" + id +
                ";\tCustomer name = " + customer.getFirstName() + " " + customer.getLastName() +
                ",\tage = " + customer.getAge() +
                ";\tProduct name = " + product.getName() +
                ", price = " + product.getPrice() +
                '}';
    }
}
