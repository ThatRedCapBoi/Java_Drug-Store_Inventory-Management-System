/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import infra.DBManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Category;

/**
 *
 * @author Itadori
 */
public class MySqlCategoryRepo implements CategoryRepo {

    private final DBManager db;

    public MySqlCategoryRepo(DBManager db) {
        this.db = db;
    }

    @Override
    public List<Category> findAll() {
        String sql = "SELECT id, name, description, created_at, updated_at FROM categories ORDER BY name";
        List<Category> list = new ArrayList<>();

        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Category cat = new Category();
                cat.setId(rs.getLong("id"));
                cat.setName(rs.getString("name"));
                cat.setDescription(rs.getString("description"));
                java.sql.Timestamp created = rs.getTimestamp("created_at");
                if (created != null) {
                    cat.setCreatedAt(created.toLocalDateTime());
                }
                java.sql.Timestamp updated = rs.getTimestamp("updated_at");
                if (updated != null) {
                    cat.setUpdatedAt(updated.toLocalDateTime());
                }
                list.add(cat);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Category findAll failed: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Category> findById(long id) {
        String sql = "SELECT id, name, description, created_at, updated_at FROM categories WHERE id = ?";

        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }

                Category cat = new Category();
                cat.setId(rs.getLong("id"));
                cat.setName(rs.getString("name"));
                cat.setDescription(rs.getString("description"));
                java.sql.Timestamp created = rs.getTimestamp("created_at");
                if (created != null) {
                    cat.setCreatedAt(created.toLocalDateTime());
                }
                java.sql.Timestamp updated = rs.getTimestamp("updated_at");
                if (updated != null) {
                    cat.setUpdatedAt(updated.toLocalDateTime());
                }
                return Optional.of(cat);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Category findById failed: " + e.getMessage(), e);
        }
    }

    @Override
    public Category save(Category cat) {
        String sql = "INSERT INTO categories(name, description) VALUES (?, ?)";

        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cat.getName());
            ps.setString(2, cat.getDescription());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    cat.setId(keys.getLong(1));
                }
            }
            return cat;

        } catch (SQLException e) {
            throw new RuntimeException("Category save failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Category cat) {
        String sql = "UPDATE categories SET name = ?, description = ? WHERE id = ?";

        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, cat.getName());
            ps.setString(2, cat.getDescription());
            ps.setLong(3, cat.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Category update failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM categories WHERE id = ?";

        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Category delete failed: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean existsByName(String name) {
        String sql = "SELECT 1 FROM categories WHERE LOWER(name) = LOWER(?) LIMIT 1";

        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Category existsByName failed: " + e.getMessage(), e);
        }
    }
}
