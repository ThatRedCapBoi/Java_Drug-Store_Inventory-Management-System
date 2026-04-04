package repository;

import infra.DBManager;
import java.sql.*;
import java.util.Optional;
import model.User;

public class MySqlUserRepo implements UserRepo {

    private final DBManager db;

    public MySqlUserRepo(DBManager db) {
        this.db = db;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT id, username, password_hash, role FROM users WHERE username = ? LIMIT 1";

        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                User u = new User();
                u.setId(rs.getLong(1));
                u.setUsername(rs.getString(2));
                u.setPasswordHash(rs.getString(3));
                u.setRole(rs.getString(4));

                return Optional.of(u);
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB error in findByUsername: " + e.getMessage(), e);
        }
    }
}
