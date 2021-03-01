package service;

import dao.implementations.ProductDaoMysqlImpl;
import models.Order;
import models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private final ProductDaoMysqlImpl productDaoMysql = new ProductDaoMysqlImpl();

    public void createProduct(Product product) {
            productDaoMysql.create(product);
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products;
        products = productDaoMysql.findAll();
        return products;
    }

    public Product getProductById(int id) {
        return productDaoMysql.findById(id);
    }

    public void updateProductById(int id, Product product) {
        productDaoMysql.updateById(id, product);
    }

    public void deleteProductById(int id) {
        productDaoMysql.deleteById(id);
    }

    public void deleteAllProducts() {
        productDaoMysql.deleteAll();
    }

    public void printAllProducts() {
        List<Product> products = productDaoMysql.findAll();
        for (Product product : products) System.out.println(product.toString());
    }

}
