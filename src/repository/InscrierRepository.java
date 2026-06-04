package repository;

import config.DatabaseConnection;
import model.Inscriere;
import model.Cursant;
import model.Curs;


import java.sql.*;

public class InscrierRepository extends CursRepository.GenericRepository<Inscriere> {

    @Override
    protected Inscriere mapRow(ResultSet rs) throws SQLException {
        int cursantId = rs.getInt("cursant_id");
        int cursId = rs.getInt("curs_id");

        Cursant cursant = null;
        String sqlCursant = "SELECT * FROM utilizatori WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlCursant)) {
            stmt.setInt(1, cursantId);
            ResultSet rs2 = stmt.executeQuery();
            if (rs2.next()) {
                cursant = new Cursant(
                        rs2.getInt("id"),
                        rs2.getString("nume"),
                        rs2.getString("email")
                );
            }
        }

        Curs curs = null;
        CursRepository cursRepo = new CursRepository();
        curs = cursRepo.findById(cursId);

        Inscriere inscriere = new Inscriere(rs.getInt("id"), cursant, curs);
        if (rs.getBoolean("finalizat")) {
            inscriere.finalizeaza();
        }
        return inscriere;
    }

    @Override
    public void save(Inscriere i) throws SQLException {
        String sql = "INSERT INTO inscrieri(id, cursant_id, curs_id, finalizat) VALUES (?, ?, ?, ?) ON CONFLICT (id) DO NOTHING";
        executeUpdate(sql, i.getId(), i.getCursant().getId(), i.getCurs().getId(), i.isFinalizat());
    }

    @Override
    public void update(Inscriere i) throws SQLException {
        String sql = "UPDATE inscrieri SET finalizat = ? WHERE id = ?";
        executeUpdate(sql, i.isFinalizat(), i.getId());
    }

    @Override
    protected String getFindByIdSql() {
        return "SELECT * FROM inscrieri WHERE id = ?";
    }

    @Override
    protected String getFindAllSql() {
        return "SELECT * FROM inscrieri";
    }

    @Override
    protected String getDeleteSql() {
        return "DELETE FROM inscrieri WHERE id = ?";
    }
}