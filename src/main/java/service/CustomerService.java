package service;

import dao.implementations.CustomerDaoMysqlImpl;
import models.Customer;

import java.util.ArrayList;

public class CustomerService {

    CustomerDaoMysqlImpl customerDaoMysql = new CustomerDaoMysqlImpl();

    public void createCustomer(Customer customer) {
        customerDaoMysql.create(customer);
    }

    public ArrayList<Customer> getAllCustomers() {
        return customerDaoMysql.findAll();
    }

    public Customer getCustomerById(int id) {
        return customerDaoMysql.findById(id);
    }

    public void deleteCustomerById(int id) {
        customerDaoMysql.deleteById(id);
    }

    public void deleteAllCustomers() {
        customerDaoMysql.deleteAll();
    }

}
