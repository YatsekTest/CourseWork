package service;

import dao.implementations.CustomerDaoMysqlImpl;
import models.Customer;
import models.Product;

import java.util.ArrayList;
import java.util.List;

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

    public void updateCustomerById(int id, Customer customer) {
        customerDaoMysql.updateById(id, customer);
    }

    public void deleteCustomerById(int id) {
        customerDaoMysql.deleteById(id);
    }

    public void deleteAllCustomers() {
        customerDaoMysql.deleteAll();
    }

    public void printAllCustomers() {
        List<Customer> customers = customerDaoMysql.findAll();
        for (Customer customer : customers) System.out.println(customer.toString());
    }

}
