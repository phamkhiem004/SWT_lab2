/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import models.RentalContract;
import models.Vehicle;

/**
 *
 * @author SHD
 */
public class RentalContractDAO extends DBContext {

    public int createRentalContract(RentalContract contract) {
        String sql = "INSERT INTO RentalContract (UserId, ExpectedPickupDateTime, ExpectedReturnDateTime, DepositAmount, TotalAmount, Status) VALUES (?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, contract.getUserId());
            statement.setTimestamp(2, (Timestamp) contract.getExpectedPickupDateTime());
            statement.setTimestamp(3, (Timestamp) contract.getExpectedReturnDateTime());
            statement.setDouble(4, contract.getDepositAmount());
            statement.setDouble(5, contract.getTotalAmount());
            statement.setInt(6, contract.getStatus());
            statement.executeUpdate();

            // Get the generated contract ID
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void updateTotalMoney(double total, int id) {
        String sql = "Update RentalContract set TotalAmount = ? where ContractId = ?";
        try ( PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(2, id);
            statement.setDouble(1, total);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<RentalContract> getRentalsByUserId(int userId) throws SQLException {
        List<RentalContract> rentals = new ArrayList<>();
        String sql = "SELECT * FROM RentalContract WHERE UserId = ? order by ContractId desc";

        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                RentalContract rental = new RentalContract();
                rental.setContractId(rs.getInt("ContractId"));
                rental.setUserId(rs.getInt("UserId"));
                rental.setExpectedPickupDateTime(rs.getTimestamp("ExpectedPickupDateTime"));
                rental.setExpectedReturnDateTime(rs.getTimestamp("ExpectedReturnDateTime"));
                rental.setDepositAmount(rs.getDouble("DepositAmount"));
                rental.setTotalAmount(rs.getDouble("TotalAmount"));
                rental.setStatus(rs.getInt("Status"));
                rentals.add(rental);
            }
        }

        return rentals;
    }
    
    public void updateRentalStatus(int contractId, int newStatus) {
        String sql = "UPDATE RentalContract SET Status = ? WHERE ContractId = ?";
        try (
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, newStatus);
            stmt.setInt(2, contractId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<RentalContract> getAllRentals() throws SQLException {
        List<RentalContract> rentals = new ArrayList<>();
        String sql = "SELECT * FROM RentalContract order by ContractId desc";

        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                RentalContract rental = new RentalContract();
                rental.setContractId(rs.getInt("ContractId"));
                rental.setUserId(rs.getInt("UserId"));
                rental.setExpectedPickupDateTime(rs.getTimestamp("ExpectedPickupDateTime"));
                rental.setExpectedReturnDateTime(rs.getTimestamp("ExpectedReturnDateTime"));
                rental.setDepositAmount(rs.getDouble("DepositAmount"));
                rental.setTotalAmount(rs.getDouble("TotalAmount"));
                rental.setStatus(rs.getInt("Status"));
                rentals.add(rental);
            }
        }

        return rentals;
    }

    // Lấy thông tin chi tiết xe trong hợp đồng thuê
    public List<Vehicle> getVehiclesByContractId(int contractId) throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT v.* FROM Vehicle v "
                + "JOIN ContractDetail cd ON v.VehicleId = cd.VehicleId "
                + "WHERE cd.ContractId = ?";

        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, contractId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleId(rs.getInt("VehicleId"));
                vehicle.setVehicleTypeId(rs.getInt("VehicleTypeId"));
                vehicle.setLicensePlate(rs.getString("LicensePlate"));
                vehicle.setBrand(rs.getString("Brand"));
                vehicle.setModel(rs.getString("Model"));
                vehicle.setYear(rs.getInt("Year"));
                vehicle.setRentalPricePerDay(rs.getDouble("RentalPricePerDay"));
                vehicle.setStatus(rs.getInt("Status"));
                vehicle.setImageUrl(rs.getString("ImageURL"));
                vehicles.add(vehicle);
            }
        }

        return vehicles;
    }
    
    public RentalContract getRentalById(int contractId) throws SQLException {
        RentalContract rental = null;
        String sql = "SELECT * FROM RentalContract WHERE ContractId = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, contractId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                rental = new RentalContract();
                rental.setContractId(rs.getInt("ContractId"));
                rental.setUserId(rs.getInt("UserId"));
                rental.setExpectedPickupDateTime(rs.getTimestamp("ExpectedPickupDateTime"));
                rental.setExpectedReturnDateTime(rs.getTimestamp("ExpectedReturnDateTime"));
                rental.setDepositAmount(rs.getDouble("DepositAmount"));
                rental.setTotalAmount(rs.getDouble("TotalAmount"));
                rental.setStatus(rs.getInt("Status"));
            }
        }
        
        return rental;
    }

    public void addVehicleToContract(int contractId, int vehicleId) {
        String sql = "INSERT INTO ContractDetail (ContractId, VehicleId) VALUES (?, ?)";
        try (
                 PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, contractId);
            statement.setInt(2, vehicleId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
