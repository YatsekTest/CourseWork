import service.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        Database database = new Database();

//        database.createTable("CREATE TABLE customers " +
//                "(id INT AUTO_INCREMENT PRIMARY KEY, " +
//                "first_name VARCHAR(30), " +
//                "last_name VARCHAR(30), " +
//                "age INT);");

//        database.createTable("CREATE TABLE products " +
//                "(id INT AUTO_INCREMENT PRIMARY KEY, " +
//                "name VARCHAR(30), " +
//                "price INT);");

//        database.createTable("CREATE TABLE orders " +
//                "(id INT AUTO_INCREMENT PRIMARY KEY, " +
//                "customer_id INT, " +
//                "product_id INT, " +
//                "FOREIGN KEY (customer_id) REFERENCES customers(id), " +
//                "FOREIGN KEY (product_id) REFERENCES products(id));");

    }
}
