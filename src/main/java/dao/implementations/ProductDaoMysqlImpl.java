package dao.implementations;

import dao.interfaces.ProductDao;
import models.Customer;
import models.Product;
import service.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoMysqlImpl implements ProductDao {

    @Override
    public void create(Product product) {
        String sql = "INSERT INTO products (name, price) VALUES (?, ?);";
        try (Connection connection = Database.getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.executeUpdate();
            System.out.println("Product successfully added into products table.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM products;";
        try (Connection connection = Database.getDbConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                productList.add(new Product(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product findById(Integer id) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE id = " + id + ";";
        try (Connection connection = Database.getDbConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void delete(Product product) {

    }
}
