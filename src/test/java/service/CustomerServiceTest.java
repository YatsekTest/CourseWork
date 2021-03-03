package service;

import models.Customer;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class CustomerServiceTest {

    ProductService productService = new ProductService();
    CustomerService customerService = new CustomerService();
    OrderService orderService = new OrderService();

    @BeforeTest
    public void setUp() {
        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        productService.deleteAllProducts();
    }

    @BeforeMethod
    public void setUpMethod() {
        for (int i = 1; i <= 10; i++) {
            customerService.createCustomer(new Customer("Name" + i, "Surname" + i, (int) (Math.random() * 71) + 21));
        }
    }

    @AfterMethod
    public void tearDown() {
        customerService.deleteAllCustomers();
    }

    @Test
    public void testCreateCustomer() {
        customerService.createCustomer(new Customer("NameEleven", "SurnameEleven", 30));
        Assert.assertEquals(customerService.getCustomerById(11).getAge(), 30);
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        Assert.assertEquals(customers.size(), 10);
    }

    @Test
    public void testGetCustomerById() {
        Customer customer = customerService.getCustomerById(7);
        Assert.assertEquals(customer.getFirstName(), "Name7");
    }

    @Test
    public void testUpdateCustomerById() {
        customerService.updateCustomerById(3, customerService.getCustomerById(5));
        Assert.assertEquals(customerService.getCustomerById(3).getFirstName(), "Name5");
    }

    @Test
    public void testDeleteCustomerById() {
        customerService.deleteCustomerById(10);
        customerService.deleteCustomerById(1);
        List<Customer> customers = customerService.getAllCustomers();
        Assert.assertEquals(customers.size(), 8);
    }

    @Test
    public void testDeleteAllCustomers() {
        customerService.deleteAllCustomers();
        List<Customer> customers = customerService.getAllCustomers();
        Assert.assertEquals(customers.size(), 0);
    }
}