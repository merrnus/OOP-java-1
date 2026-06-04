package repository;

import config.DatabaseConnection;
import model.Curs;
import model.Instructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursRepository extends GenericRepository<Curs> {

    @Override
    protected Curs mapRow(ResultSet rs) throws SQLException {
        int instructorId = rs.getInt("instructor_id");
        String sql = "SELECT * FROM utilizatori WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, instructorId);
            ResultSet rs2 = stmt.executeQuery();
            if (rs2.next()) {
                Instructor instructor = new Instructor(
                        rs2.getInt("id"),
                        rs2.getString("nume"),
                        rs2.getString("email")
                );
                return new Curs(
                        rs.getInt("id"),
                        rs.getString("titlu"),
                        instructor
                );
            }
        }
        return null;
    }

    @Override
    public void save(Curs c) throws SQLException {
        String sql = "INSERT INTO cursuri(id, titlu, instructor_id) VALUES (?, ?, ?) ON CONFLICT (id) DO NOTHING";
        executeUpdate(sql, c.getId(), c.getTitlu(), c.getInstructor().getId());
    }

    @Override
    public void update(Curs c) throws SQLException {
        String sql = "UPDATE cursuri SET titlu = ? WHERE id = ?";
        executeUpdate(sql, c.getTitlu(), c.getId());
    }

    @Override
    protected String getFindByIdSql() {
        return "SELECT * FROM cursuri WHERE id = ?";
    }

    @Override
    protected String getFindAllSql() {
        return "SELECT * FROM cursuri";
    }

    @Override
    protected String getDeleteSql() {
        return "DELETE FROM cursuri WHERE id = ?";
    }

    public abstract static class GenericRepository<T> {

        protected abstract T mapRow(ResultSet rs) throws SQLException;
        public abstract void save(T entity) throws SQLException;
        public abstract void update(T entity) throws SQLException;
        protected abstract String getFindByIdSql();
        protected abstract String getFindAllSql();
        protected abstract String getDeleteSql();

        public T findById(int id) throws SQLException {
            try (Connection conn = DatabaseConnection.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(getFindByIdSql())) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) return mapRow(rs);
            }
            return null;
        }

        public List<T> findAll() throws SQLException {
            List<T> results = new ArrayList<>();
            try (Connection conn = DatabaseConnection.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(getFindAllSql())) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) results.add(mapRow(rs));
            }
            return results;
        }

        public void delete(int id) throws SQLException {
            executeUpdate(getDeleteSql(), id);
        }

        protected void executeUpdate(String sql, Object... params) throws SQLException {
            try (Connection conn = DatabaseConnection.getInstance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                for (int i = 0; i < params.length; i++) {
                    stmt.setObject(i + 1, params[i]);
                }
                stmt.executeUpdate();
            }
        }
    }
}