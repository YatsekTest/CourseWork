package service;

import models.Customer;
import models.Order;
import models.Product;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class OrderServiceTest {

    ProductService productService = new ProductService();
    CustomerService customerService = new CustomerService();
    OrderService orderService = new OrderService();

    @BeforeTest
    public void setUp() {
        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        productService.deleteAllProducts();
        for (int i = 1; i <= 10; i++) {
            productService.createProduct(new Product("Product" + i, (int) (Math.random() * 91) + 10));
            customerService.createCustomer(new Customer("Name" + i, "Surname" + i, (int) (Math.random() * 71) + 21));
        }
    }

    @BeforeMethod
    public void setUpMethod() {
        for (int i = 1; i <= 10; i++) {
            orderService.createOrder(new Order(customerService.getCustomerById(i), productService.getProductById(i)));
        }
    }

    @AfterMethod
    public void tearDown() {
        orderService.deleteAllOrders();
    }

    @Test
    public void testCreateOrder() {
        orderService.createOrder(new Order(customerService.getCustomerById(10), productService.getProductById(1)));
        Assert.assertEquals(orderService.getOrderById(11).getCustomer().getLastName(), "Surname10");
        Assert.assertEquals(orderService.getOrderById(11).getProduct().getName(), "Product1");
    }

    @Test
    public void testGetAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        Assert.assertEquals(10, orders.size());
    }

    @Test
    public void testGetOrderById() {
        Order order = orderService.getOrderById(10);
        Assert.assertEquals(order.getCustomer().getLastName(), "Surname10");
        Assert.assertEquals(order.getProduct().getName(), "Product10");
    }

    @Test
    public void testUpdateOrderById() {
        Customer customer = customerService.getCustomerById(1);
        Product product = productService.getProductById(9);
        Order order = new Order(customer, product);
        orderService.updateOrderById(7, order);
        Assert.assertEquals(orderService.getOrderById(7).getCustomer().getFirstName(), "Name1");
        Assert.assertEquals(orderService.getOrderById(7).getProduct().getName(), "Product9");
    }

    @Test
    public void testDeleteOrderById() {
        orderService.deleteOrderById(3);
        List<Order> orders = orderService.getAllOrders();
        Assert.assertEquals(orders.size(), 9);
    }

    @Test
    public void testDeleteAllOrders() {
        orderService.deleteAllOrders();
        List<Order> orders = orderService.getAllOrders();
        Assert.assertEquals(orders.size(), 0);
    }
}