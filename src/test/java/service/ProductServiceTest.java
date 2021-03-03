package service;

import models.Product;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class ProductServiceTest {

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
            productService.createProduct(new Product("Product-" + i, (int) (Math.random() * 91) + 10));
        }
    }

    @AfterMethod
    public void tearDown() {
        productService.deleteAllProducts();
    }

    @Test
    public void testCreateProduct() {
        productService.createProduct(new Product("ProductEleven", 50));
        Assert.assertEquals(productService.getProductById(11).getName(), "ProductEleven");
        Assert.assertEquals(productService.getProductById(11).getPrice(), 50);
    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();
        Assert.assertEquals(products.size(), 10);
    }

    @Test
    public void testGetProductById() {
        Product product = productService.getProductById(3);
        Assert.assertEquals(product.getName(), "Product-3");
    }

    @Test
    public void testUpdateProductById() {
        productService.updateProductById(5, new Product("JustProduct", 0));
        Assert.assertEquals(productService.getProductById(5).getPrice(), 0);
        Assert.assertEquals(productService.getProductById(5).getName(), "JustProduct");
    }

    @Test
    public void testDeleteProductById() {
        productService.deleteProductById(1);
        productService.deleteProductById(2);
        productService.deleteProductById(3);
        List<Product> products = productService.getAllProducts();
        Assert.assertEquals(products.size(), 7);
    }

    @Test
    public void testDeleteAllProducts() {
        productService.deleteAllProducts();
        List<Product> products = productService.getAllProducts();
        Assert.assertEquals(products.size(), 0);
    }
}