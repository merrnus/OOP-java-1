import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LectieRepository {

    // INSERT
    public void save(Lectie l, int cursId) throws Exception {
        String sql = "INSERT INTO lectii(id, titlu, continut, curs_id) VALUES (?, ?, ?, ?) ON CONFLICT (id) DO NOTHING";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, l.getId());
            stmt.setString(2, l.getTitlu());
            stmt.setString(3, l.getContinut());
            stmt.setInt(4, cursId);
            stmt.executeUpdate();
        }
    }

    // SELECT ALL
    public List<String> findAll() throws Exception {
        String sql = "SELECT l.id, l.titlu, c.titlu as curs FROM lectii l JOIN cursuri c ON l.curs_id = c.id";
        List<String> lista = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String linie = "Lectie{id=" + rs.getInt("id") +
                        ", titlu=" + rs.getString("titlu") +
                        ", curs=" + rs.getString("curs") + "}";
                lista.add(linie);
            }
        }
        return lista;
    }

    // UPDATE
    public void update(Lectie l) throws Exception {
        String sql = "UPDATE lectii SET titlu = ?, continut = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, l.getTitlu());
            stmt.setString(2, l.getContinut());
            stmt.setInt(3, l.getId());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM lectii WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}