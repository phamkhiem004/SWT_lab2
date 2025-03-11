/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import context.DBContext;
import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.UserAccount;

/**
 *
 * @author SHD
 */
public class UserAccountDAO extends DBContext {

    public UserAccount getUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM UserAccount WHERE UserId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    UserAccount user = new UserAccount();
                    user.setUserId(rs.getInt("UserId"));
                    user.setUsername(rs.getString("Username"));
                    user.setFullName(rs.getString("FullName"));
                    user.setRole(rs.getInt("Role"));
                    user.setStatus(rs.getInt("Status"));
                    user.setEmail(rs.getString("Email"));
                    return user;
                }
            }
        }
        return null; // Return null if no user is found with the given ID
    }
    
    public UserAccount getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM UserAccount WHERE Username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    UserAccount user = new UserAccount();
                    user.setUserId(rs.getInt("UserId"));
                    user.setUsername(rs.getString("Username"));
                    user.setFullName(rs.getString("FullName"));
                    user.setRole(rs.getInt("Role"));
                    user.setStatus(rs.getInt("Status"));
                    user.setEmail(rs.getString("Email"));
                    return user;
                }
            }
        }
        return null; // Return null if no user is found with the given ID
    }

    public boolean registerUser(UserAccount user) {
        String query = "INSERT INTO UserAccount (Username, PasswordHash, FullName, Email, Phone, Role,CreatedDate,Status) VALUES (?, ?, ?, ?, ?, ?,GETDATE(),0 )";
        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhone());
            ps.setInt(6, user.getRole());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra thông tin đăng nhập
    public UserAccount loginUser(String username, String passwordHash) {
        String query = "SELECT * FROM UserAccount WHERE Username = ? AND PasswordHash = ?";
        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, passwordHash);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UserAccount user = new UserAccount();
                user.setUserId(rs.getInt("UserId"));
                user.setUsername(rs.getString("Username"));
                user.setPasswordHash(rs.getString("PasswordHash"));
                user.setFullName(rs.getString("FullName"));
                user.setEmail(rs.getString("Email"));
                user.setPhone(rs.getString("Phone"));
                user.setRole(rs.getInt("Role"));
                user.setStatus(rs.getInt("Status"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createUser(UserAccount user) throws SQLException {
        String sql = "INSERT INTO UserAccount (Username, PasswordHash, Role, Status, FullName, Email) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());
            stmt.setInt(3, user.getRole());
            stmt.setInt(4, user.getStatus());
            stmt.setString(5, user.getFullName());
            stmt.setString(6, user.getEmail());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean updateUser(UserAccount user) throws SQLException {
        String sql = "UPDATE UserAccount SET FullName = ?, Role = ?, Status = ?, Email = ? WHERE UserId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getFullName());
            stmt.setInt(2, user.getRole());
            stmt.setInt(3, user.getStatus());
            stmt.setString(4, user.getEmail());
            stmt.setInt(5, user.getUserId());
            return stmt.executeUpdate() > 0;
        }
    }

    public List<UserAccount> getAllUsers() throws SQLException {
        List<UserAccount> users = new ArrayList<>();
        String sql = "SELECT * FROM UserAccount";
        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                UserAccount user = new UserAccount();
                user.setUserId(rs.getInt("UserId"));
                user.setUsername(rs.getString("Username"));
                user.setRole(rs.getInt("Role"));
                user.setStatus(rs.getInt("Status"));
                user.setFullName(rs.getString("FullName"));
                user.setEmail(rs.getString("Email"));
                users.add(user);
            }
        }
        return users;
    }

    public static void main(String[] args) {
        try {
            System.out.println(new UserAccountDAO().getAllUsers());
        } catch (SQLException ex) {
            Logger.getLogger(UserAccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean toggleUserStatus(int userId) throws SQLException {
        String sql = "UPDATE UserAccount SET Status = CASE WHEN Status = 0 THEN 1 ELSE 0 END WHERE UserId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            return stmt.executeUpdate() > 0;
        }
    }

}
