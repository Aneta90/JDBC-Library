package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class ConnectionToDatabase {

    private static String url = "jdbc:mysql://localhost:3306/library";
    private static String username = "root";
    private static String password = "1234";
    private static Connection con;

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection(url + "?serverTimezone=" + TimeZone.getDefault().getID(), username, password);
        } catch (SQLException ex) {
            System.out.println("Failed to create the database connection.");
        }
        return con;
    }
}
