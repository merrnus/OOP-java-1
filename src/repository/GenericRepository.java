package repository;

import config.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericRepository<T> {

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