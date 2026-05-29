import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/platforma_elearning";
    private static final String USER = "merrnus";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}