package service;

import dao.implementations.OrderDaoMysqlImpl;
import models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final OrderDaoMysqlImpl orderDaoMysql = new OrderDaoMysqlImpl();

    public void createOrder(Order order) {
        orderDaoMysql.create(order);
    }

    public ArrayList<Order> getAllOrders() {
        return orderDaoMysql.findAll();
    }

    public Order getOrderById(int id) {
        return orderDaoMysql.findById(id);
    }

    public void deleteOrderById(int id) {
        orderDaoMysql.deleteById(id);
    }

    public void deleteAllOrders() {
        orderDaoMysql.deleteAll();
    }

}
