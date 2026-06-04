package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Gestioneaza conexiunea la baza de date PostgreSQL
// Foloseste pattern-ul Singleton pentru a asigura o singura conexiune activa
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private static final String URL = "jdbc:postgresql://localhost:5432/platforma_elearning";
    private static final String USER = "merrnus";
    private static final String PASSWORD = "";

    private DatabaseConnection() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Returneaza singura instanta a conexiunii
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Returneaza conexiunea activa
    // Daca conexiunea este inchisa, deschide una noua
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}