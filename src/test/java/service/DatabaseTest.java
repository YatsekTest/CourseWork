package service;

import org.junit.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseTest {

    @Test(timeOut = 1000)
    public void testDatabaseConnection() {
        try (Connection connection = Database.getDbConnection()) {
            Assert.assertTrue(connection.isValid(1000));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}