package service;

import dao.implementations.ProductDaoMysqlImpl;
import models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private final ProductDaoMysqlImpl productDaoMysql = new ProductDaoMysqlImpl();

    public void createProduct(Product product) {
            productDaoMysql.create(product);
    }

    public ArrayList<Product> getAllProducts() {
        List<Product> products;
        products = productDaoMysql.findAll();
        return (ArrayList<Product>) products;
    }

    public Product getProductById(int id) {
        return productDaoMysql.findById(id);
    }

}
