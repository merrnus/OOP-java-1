package repository;

import model.Lectie;
import java.sql.*;

public class LectieRepository extends CursRepository.GenericRepository<Lectie> {

    private int cursId;

    public void setCursId(int cursId) {
        this.cursId = cursId;
    }

    @Override
    protected Lectie mapRow(ResultSet rs) throws SQLException {
        return new Lectie(
                rs.getInt("id"),
                rs.getString("titlu"),
                rs.getString("continut")
        );
    }

    @Override
    public void save(Lectie l) throws SQLException {
        String sql = "INSERT INTO lectii(id, titlu, continut, curs_id) VALUES (?, ?, ?, ?) ON CONFLICT (id) DO NOTHING";
        executeUpdate(sql, l.getId(), l.getTitlu(), l.getContinut(), cursId);
    }

    @Override
    public void update(Lectie l) throws SQLException {
        String sql = "UPDATE lectii SET titlu = ?, continut = ? WHERE id = ?";
        executeUpdate(sql, l.getTitlu(), l.getContinut(), l.getId());
    }

    @Override
    protected String getFindByIdSql() {
        return "SELECT * FROM lectii WHERE id = ?";
    }

    @Override
    protected String getFindAllSql() {
        return "SELECT * FROM lectii";
    }

    @Override
    protected String getDeleteSql() {
        return "DELETE FROM lectii WHERE id = ?";
    }
}