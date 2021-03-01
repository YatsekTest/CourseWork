package dao.implementations;

import dao.interfaces.CustomerDao;
import models.Customer;
import service.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoMysqlImpl implements CustomerDao {

    private final String tableName = "customers";
    private final String id = "id";
    private final String firstName = "first_name";
    private final String lastName = "last_name";
    private final String age = "age";

    @Override
    public void create(Customer customer) {
        String sql = "INSERT INTO " + tableName + " (first_name, last_name, age) VALUES (?, ?, ?);";
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
    public List<Customer> findAll() {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + ";";
        try (Connection connection = Database.getDbConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                customerList.add(new Customer(resultSet.getInt(id),
                        resultSet.getString(firstName),
                        resultSet.getString(lastName),
                        resultSet.getInt(age)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    @Override
    public Customer findById(Integer id) {
        Customer customer = new Customer();
        String sql = "SELECT * FROM " + tableName + " WHERE id = " + id + ";";
        try (Connection connection = Database.getDbConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                customer.setId(resultSet.getInt(this.id));
                customer.setFirstName(resultSet.getString(firstName));
                customer.setLastName(resultSet.getString(lastName));
                customer.setAge(resultSet.getInt(age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void updateById(Integer id, Customer customer) {
        String sql = "UPDATE " + tableName + " SET first_name = ?, last_name = ?, age = ? WHERE id = ?;";
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
        String sql = "DELETE FROM " + tableName + " WHERE id = " + id + ";";
        try (Connection connection = Database.getDbConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
