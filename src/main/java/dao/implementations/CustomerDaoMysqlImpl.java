package dao.implementations;

import dao.interfaces.CustomerDao;
import models.Customer;
import service.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoMysqlImpl implements CustomerDao {

    private final static String TABLE_NAME = "customers";
    private final static String ID = "id";
    private final static String FIRST_NAME = "first_name";
    private final static String LAST_NAME = "last_name";
    private final static String AGE = "age";

    @Override
    public void create(Customer customer) {
        String sql = "INSERT INTO " + TABLE_NAME + " (first_name, last_name, age) VALUES (?, ?, ?);";
        try (Connection connection = Database.getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setInt(3, customer.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Customer> findAll() {
        ArrayList<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + ";";
        try (Connection connection = Database.getDbConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                customerList.add(new Customer(resultSet.getInt(ID),
                        resultSet.getString(FIRST_NAME),
                        resultSet.getString(LAST_NAME),
                        resultSet.getInt(AGE)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    @Override
    public Customer findById(Integer id) {
        Customer customer = new Customer();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = " + id + ";";
        try (Connection connection = Database.getDbConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                customer.setId(resultSet.getInt(ID));
                customer.setFirstName(resultSet.getString(FIRST_NAME));
                customer.setLastName(resultSet.getString(LAST_NAME));
                customer.setAge(resultSet.getInt(AGE));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void updateById(Integer id, Customer customer) {
        String sql = "UPDATE " + TABLE_NAME + " SET first_name = ?, last_name = ?, age = ? WHERE id = ?;";
        try (Connection connection = Database.getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setInt(3, customer.getAge());
            preparedStatement.setInt(4, id);
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
        String sql1 = "DELETE FROM " + TABLE_NAME + ";";
        String sql2 = "ALTER TABLE " + TABLE_NAME + " AUTO_INCREMENT = 1;";
        try (Connection connection = Database.getDbConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
