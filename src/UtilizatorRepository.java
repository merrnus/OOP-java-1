import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilizatorRepository {

    // INSERT
    public void save(Utilizator u) throws Exception {
        String sql = "INSERT INTO utilizatori(id, nume, email) VALUES (?, ?, ?) ON CONFLICT (id) DO NOTHING";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, u.getId());
            stmt.setString(2, u.getNume());
            stmt.setString(3, u.getEmail());
            stmt.executeUpdate();
        }
    }

    // SELECT ALL
    public List<Utilizator> findAll() throws Exception {
        String sql = "SELECT * FROM utilizatori";
        List<Utilizator> lista = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Utilizator u = new Utilizator(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("email")
                );
                lista.add(u);
            }
        }
        return lista;
    }

    // UPDATE
    public void update(Utilizator u) throws Exception {
        String sql = "UPDATE utilizatori SET nume = ?, email = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getNume());
            stmt.setString(2, u.getEmail());
            stmt.setInt(3, u.getId());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM utilizatori WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}