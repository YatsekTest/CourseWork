package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private final static String HOST = "localhost";
    private final static String PORT = "3307";
    private final static String DB_NAME = "internet_store";
    private final static String LOGIN = "root";
    private final static String PASSWORD = "root";

    private static Connection connection = null;

    public static Connection getDbConnection() {
        String connectionStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
        try {
            connection = DriverManager.getConnection(connectionStr, LOGIN, PASSWORD);
//            System.out.println("Connection to database " + DB_NAME + " is successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void createTable(String sql) {
        try (Statement statement = getDbConnection().createStatement()) {
            statement.executeUpdate(sql);
//            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
