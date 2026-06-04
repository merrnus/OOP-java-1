package repository;

import model.Cursant;
import model.Instructor;
import model.Utilizator;
import java.sql.*;

public class UtilizatorRepository extends GenericRepository<Utilizator> {

    @Override
    protected Utilizator mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nume = rs.getString("nume");
        String email = rs.getString("email");
        String tip = rs.getString("tip");

        if ("Instructor".equals(tip)) {
            return new Instructor(id, nume, email);
        } else if ("Cursant".equals(tip)) {
            return new Cursant(id, nume, email);
        }
        return new Utilizator(id, nume, email);
    }

    @Override
    public void save(Utilizator u) throws SQLException {
        String tip = u instanceof Instructor ? "Instructor" : u instanceof Cursant ? "Cursant" : "Utilizator";
        String sql = "INSERT INTO utilizatori(id, nume, email, tip) VALUES (?, ?, ?, ?) ON CONFLICT (id) DO NOTHING";
        executeUpdate(sql, u.getId(), u.getNume(), u.getEmail(), tip);
    }

    @Override
    public void update(Utilizator u) throws SQLException {
        String sql = "UPDATE utilizatori SET nume = ?, email = ? WHERE id = ?";
        executeUpdate(sql, u.getNume(), u.getEmail(), u.getId());
    }

    @Override
    protected String getFindByIdSql() {
        return "SELECT * FROM utilizatori WHERE id = ?";
    }

    @Override
    protected String getFindAllSql() {
        return "SELECT * FROM utilizatori";
    }

    @Override
    protected String getDeleteSql() {
        return "DELETE FROM utilizatori WHERE id = ?";
    }
}