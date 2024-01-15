package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    public static Connection connection = null;

    public static Connection getConnection() throws SQLException , ClassNotFoundException{


                    Class.forName("com.mysql.cj.jdbc.Driver");

                    String url = "jdbc:mysql://127.0.0.1:3306/?user=root";
                    String user = "root";
                    String password = "password";

                    connection = DriverManager.getConnection(url, user, password);
                    return  connection;
    }

    public static void closeConnection() {

        if (connection != null) {

            try {
                connection.close();
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

        }
    }
}
