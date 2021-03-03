import models.Customer;
import models.Order;
import models.Product;
import service.CustomerService;
import service.OrderService;
import service.ProductService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ProductService productService = new ProductService();
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();

        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        productService.deleteAllProducts();

        for (int i = 1; i < 10; i++) {
            productService.createProduct(new Product("Product" + i, (int) (Math.random() * 91) + 10));
            customerService.createCustomer(new Customer("Name" + i, "Surname" + i, (int) (Math.random() * 51) + 20));
            orderService.createOrder(new Order(customerService.getCustomerById(i), productService.getProductById(i)));
        }

        List<Order> orders = orderService.getAllOrders();
        for (Order order : orders) {
            System.out.println(order.toString());
        }

//        database.createTable("CREATE TABLE customers " +
//                "(id INT AUTO_INCREMENT PRIMARY KEY, " +
//                "first_name VARCHAR(30), " +
//                "last_name VARCHAR(30), " +
//                "age INT);");

//        database.createTable("CREATE TABLE products " +
//                "(id INT AUTO_INCREMENT PRIMARY KEY, " +
//                "name VARCHAR(30), " +
//                "price INT);");

//        database.createTable("CREATE TABLE orders " +
//                "(id INT AUTO_INCREMENT PRIMARY KEY, " +
//                "customer_id INT, " +
//                "product_id INT, " +
//                "FOREIGN KEY (customer_id) REFERENCES customers(id), " +
//                "FOREIGN KEY (product_id) REFERENCES products(id));");

    }
}
