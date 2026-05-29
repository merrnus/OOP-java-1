import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscrierRepository {

    // INSERT
    public void save(Inscriere i) throws Exception {
        String sql = "INSERT INTO inscrieri(id, cursant_id, curs_id, finalizat) VALUES (?, ?, ?, ?) ON CONFLICT (id) DO NOTHING";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, i.getId());
            stmt.setInt(2, i.getCursant().getId());
            stmt.setInt(3, i.getCurs().getId());
            stmt.setBoolean(4, i.isFinalizat());
            stmt.executeUpdate();
        }
    }

    // SELECT ALL
    public List<String> findAll() throws Exception {
        String sql = "SELECT u.nume, c.titlu, i.finalizat FROM inscrieri i " +
                "JOIN utilizatori u ON i.cursant_id = u.id " +
                "JOIN cursuri c ON i.curs_id = c.id";
        List<String> lista = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String linie = "Inscriere{cursant=" + rs.getString("nume") +
                        ", curs=" + rs.getString("titlu") +
                        ", finalizat=" + rs.getBoolean("finalizat") + "}";
                lista.add(linie);
            }
        }
        return lista;
    }

    // UPDATE
    public void update(int id, boolean finalizat) throws Exception {
        String sql = "UPDATE inscrieri SET finalizat = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, finalizat);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM inscrieri WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}