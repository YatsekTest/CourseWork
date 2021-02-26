import service.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        Database database = new Database();
        try (Connection connection = database.getDbConnection()){
            System.out.println("Соединение с базой данных установлено: " + connection.isValid(1000));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
