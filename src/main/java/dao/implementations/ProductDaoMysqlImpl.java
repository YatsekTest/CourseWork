package dao.implementations;

import dao.interfaces.ProductDao;
import models.Product;
import service.Database;

import java.sql.*;
import java.util.ArrayList;

public class ProductDaoMysqlImpl implements ProductDao {

    @Override
    public void create(Product product) {
        String sql = "INSERT INTO products (name, price) VALUES (?, ?);";
        try (Connection connection = Database.getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.executeUpdate();
//            System.out.println("Product successfully added into products table.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Product> findAll() {
        ArrayList<Product> productList = new ArrayList<>();
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
    public void updateById(Integer id, Product product) {
        String sql = "UPDATE products SET name = ?, price = ? WHERE id = ?;";
        try (Connection connection = Database.getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
//            System.out.println("Product successfully updated in products table.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM products WHERE id = " + id + ";";
        try (Connection connection = Database.getDbConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
//            System.out.println("Product successfully deleted from products table.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM products;";
        try (Connection connection = Database.getDbConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
