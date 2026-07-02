package repository;

import infra.DBManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Vendor;

public class MySqlVendorRepo implements VendorRepo {

    private final DBManager db;

    public MySqlVendorRepo(DBManager db) {
        this.db = db;
    }

    private Vendor map(ResultSet rs) throws SQLException {
        Vendor v = new Vendor();
        v.setVendorID(rs.getInt("VendorID"));
        v.setVendorName(rs.getString("VendorName"));
        v.setPersonInCharge(rs.getString("PersonInCharge"));
        v.setAddress(rs.getString("Address"));
        return v;
    }

    @Override
    public List<Vendor> findAll() {
        String sql = "SELECT VendorID, VendorName, PersonInCharge, Address FROM vendors ORDER BY VendorName";
        List<Vendor> list = new ArrayList<>();

        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(map(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Vendor findAll failed: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Vendor> findById(long id) {
        String sql = "SELECT VendorID, VendorName, PersonInCharge, Address FROM vendors WHERE VendorID = ?";

        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                return Optional.of(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Vendor findById failed: " + e.getMessage(), e);
        }
    }

    @Override
    public Vendor save(Vendor v) {
        String sql = "INSERT INTO vendors (VendorName, PersonInCharge, Address) VALUES (?, ?, ?)";

        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, v.getVendorName());
            ps.setString(2, v.getPersonInCharge());
            ps.setString(3, v.getAddress());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    v.setVendorID(keys.getInt(1));
                }
            }
            return v;
        } catch (SQLException e) {
            throw new RuntimeException("Vendor save failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Vendor v) {
        String sql = "UPDATE vendors SET VendorName=?, PersonInCharge=?, Address=? WHERE VendorID=?";

        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, v.getVendorName());
            ps.setString(2, v.getPersonInCharge());
            ps.setString(3, v.getAddress());
            ps.setInt(4, v.getVendorID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Vendor update failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM vendors WHERE VendorID=?";

        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Vendor delete failed: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Vendor> search(String query) {
        String q = (query == null) ? "" : query.trim();
        String sql = "SELECT VendorID, VendorName, PersonInCharge, Address "
                + "FROM vendors WHERE VendorName LIKE ? OR PersonInCharge LIKE ? ORDER BY VendorName";

        List<Vendor> list = new ArrayList<>();
        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + q + "%");
            ps.setString(2, "%" + q + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));
                }
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Vendor search failed: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean existsByVendorId(String vendorId) {
        // NOTE: This method needs adjustment to match the new schema, 
        // which does NOT have a 'vendor_id' string column, only the INT PK 'VendorID'.
        // If searching by ID, use VendorID. 
        return false;
    }
}
