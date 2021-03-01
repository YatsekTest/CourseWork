package dao.implementations;

import dao.interfaces.OrderDao;
import models.Order;
import service.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoMysqlImpl implements OrderDao {

    private static final String CUSTOMER_ID = "customer_id";
    private static final String PRODUCT_ID = "product_id";
    private static final String TABLE_NAME = "orders";
    private final CustomerDaoMysqlImpl customerDaoMysql = new CustomerDaoMysqlImpl();
    private final ProductDaoMysqlImpl productDaoMysql = new ProductDaoMysqlImpl();

    @Override
    public void create(Order order) {
        String sql = "INSERT INTO " + TABLE_NAME + " (customer_id, product_id) VALUES (?, ?);";
        try (Connection connection = Database.getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, order.getCustomer().getId());
            preparedStatement.setInt(2, order.getProduct().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Order> findAll() {
        ArrayList<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + ";";
        try (Connection connection = Database.getDbConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                orderList.add(new Order(resultSet.getInt("id"),
                        customerDaoMysql.findById(resultSet.getInt(CUSTOMER_ID)),
                        productDaoMysql.findById(resultSet.getInt(PRODUCT_ID))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public Order findById(Integer id) {
        Order order = new Order();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = " + id + ";";
        try (Connection connection = Database.getDbConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                order.setId(resultSet.getInt("id"));
                order.setCustomer(customerDaoMysql.findById(resultSet.getInt(CUSTOMER_ID)));
                order.setProduct(productDaoMysql.findById(resultSet.getInt(PRODUCT_ID)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void updateById(Integer id, Order order) {
        String sql = "UPDATE " + TABLE_NAME + " SET " + CUSTOMER_ID + " = ?, " + PRODUCT_ID + " = ?, WHERE id = ?;";
        try (Connection connection = Database.getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, order.getCustomer().getId());
            preparedStatement.setInt(2, order.getProduct().getId());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = " + id + ";";
        try (Connection connection = Database.getDbConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM " + TABLE_NAME + ";";
        try (Connection connection = Database.getDbConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
