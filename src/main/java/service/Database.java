package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final String HOST = "localhost";
    private final String PORT = "3307";
    private final String DB_NAME = "internet_store";
    private final String LOGIN = "root";
    private final String PASSWORD = "root";

    Connection connection = null;

    public Connection getDbConnection() throws SQLException {
        String connectionStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
        connection = DriverManager.getConnection(connectionStr, LOGIN, PASSWORD);
        System.out.println("Connection to database " + DB_NAME + " is successful.");
        return connection;
    }

}
