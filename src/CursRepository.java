import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursRepository {

    // INSERT
    public void save(Curs c) throws Exception {
        String sql = "INSERT INTO utilizatori(id, nume, email) VALUES (?, ?, ?) ON CONFLICT (id) DO NOTHING";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, c.getId());
            stmt.setString(2, c.getTitlu());
            stmt.setInt(3, c.getInstructor().getId());
            stmt.executeUpdate();
        }
    }

    // SELECT ALL
    public List<String> findAll() throws Exception {
        String sql = "SELECT c.id, c.titlu, u.nume FROM cursuri c JOIN utilizatori u ON c.instructor_id = u.id";
        List<String> lista = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String linie = "Curs{id=" + rs.getInt("id") +
                        ", titlu=" + rs.getString("titlu") +
                        ", instructor=" + rs.getString("nume") + "}";
                lista.add(linie);
            }
        }
        return lista;
    }

    // UPDATE
    public void update(Curs c) throws Exception {
        String sql = "UPDATE cursuri SET titlu = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getTitlu());
            stmt.setInt(2, c.getId());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM cursuri WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}