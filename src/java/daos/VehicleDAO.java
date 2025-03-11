/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Vehicle;
import models.VehicleType;

/**
 *
 * @author SHD
 */
public class VehicleDAO extends DBContext {

    public List<VehicleType> getAllVehicleTypes() throws SQLException {
        List<VehicleType> vehicleTypes = new ArrayList<>();
        String sql = "SELECT VehicleTypeId, TypeName FROM VehicleType";
        try ( PreparedStatement statement = connection.prepareStatement(sql);  ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                VehicleType vehicleType = new VehicleType();
                vehicleType.setVehicleTypeId(resultSet.getInt("VehicleTypeId"));
                vehicleType.setTypeName(resultSet.getString("TypeName"));
                vehicleTypes.add(vehicleType);
            }
        }
        return vehicleTypes;
    }

    public List<Vehicle> getAvailableVehicles(int vehicleTypeId, Date pickupDate, Date returnDate) throws SQLException {
        //0 là chờ xét duyệt,1 là đã cọc,2 là  đã thuê,3 là hủy,4 là thanh toán + đã trả xe
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT v.* FROM Vehicle v "
                + "WHERE v.VehicleTypeId = ? AND v.Status = 0 "
                + // Status = 0 means available
                "AND NOT EXISTS ( "
                + "    SELECT 1 FROM RentalContract rc "
                + "    JOIN ContractDetail cd ON rc.ContractId = cd.ContractId "
                + "    WHERE cd.VehicleId = v.VehicleId and rc.Status in (1,2) "
                + "    AND (rc.ExpectedPickupDateTime < ? AND rc.ExpectedReturnDateTime > ?) "
                + ")";

        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, vehicleTypeId);
            statement.setDate(2, new java.sql.Date(returnDate.getTime())); // Set end date as the first parameter
            statement.setDate(3, new java.sql.Date(pickupDate.getTime())); // Set start date as the second parameter

            try ( ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVehicleId(resultSet.getInt("VehicleId"));
                    vehicle.setVehicleTypeId(resultSet.getInt("VehicleTypeId"));
                    vehicle.setLicensePlate(resultSet.getString("LicensePlate"));
                    vehicle.setBrand(resultSet.getString("Brand"));
                    vehicle.setModel(resultSet.getString("Model"));
                    vehicle.setYear(resultSet.getInt("Year"));
                    vehicle.setRentalPricePerDay(resultSet.getDouble("RentalPricePerDay"));
                    vehicle.setStatus(resultSet.getInt("Status"));
                    vehicle.setImageUrl(resultSet.getString("ImageURL"));
                    vehicles.add(vehicle);
                }
            }
        }
        return vehicles;
    }
    
    public List<Vehicle> getAllVehicles() throws SQLException {
        //0 là chờ xét duyệt,1 là đã cọc,2 là  đã thuê,3 là hủy,4 là thanh toán + đã trả xe
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT v.* FROM Vehicle v ";

        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
          // Set start date as the second parameter

            try ( ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVehicleId(resultSet.getInt("VehicleId"));
                    vehicle.setVehicleTypeId(resultSet.getInt("VehicleTypeId"));
                    vehicle.setLicensePlate(resultSet.getString("LicensePlate"));
                    vehicle.setBrand(resultSet.getString("Brand"));
                    vehicle.setModel(resultSet.getString("Model"));
                    vehicle.setYear(resultSet.getInt("Year"));
                    vehicle.setRentalPricePerDay(resultSet.getDouble("RentalPricePerDay"));
                    vehicle.setStatus(resultSet.getInt("Status"));
                    vehicle.setImageUrl(resultSet.getString("ImageURL"));
                    vehicles.add(vehicle);
                }
            }
        }
        return vehicles;
    }

    public List<VehicleType> getVehicleTypes() {
        List<VehicleType> vehicleTypes = new ArrayList<>();
        String sql = "SELECT VehicleTypeId, TypeName FROM VehicleType";
        try (  PreparedStatement stmt = connection.prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                VehicleType vehicleType = new VehicleType();
                vehicleType.setVehicleTypeId(rs.getInt("VehicleTypeId"));
                vehicleType.setTypeName(rs.getString("TypeName"));
                vehicleTypes.add(vehicleType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleTypes;
    }

    // Tạo mới xe
    public void createVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO Vehicle (VehicleTypeId, LicensePlate, Brand, Model, Year, RentalPricePerDay, Status, ImageURL) "
                + "VALUES (?, ?, ?, ?, ?, ?, 0, ?)";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicle.getVehicleTypeId());
            stmt.setString(2, vehicle.getLicensePlate());
            stmt.setString(3, vehicle.getBrand());
            stmt.setString(4, vehicle.getModel());
            stmt.setInt(5, vehicle.getYear());
            stmt.setDouble(6, vehicle.getRentalPricePerDay());
            stmt.setString(7, vehicle.getImageUrl());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cập nhật thông tin xe
    public void updateVehicle(Vehicle vehicle) {
        String sql = "UPDATE Vehicle SET VehicleTypeId = ?, LicensePlate = ?, Brand = ?, Model = ?, Year = ?, RentalPricePerDay = ?, ImageURL = ? "
                + "WHERE VehicleId = ?";
        try (   PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicle.getVehicleTypeId());
            stmt.setString(2, vehicle.getLicensePlate());
            stmt.setString(3, vehicle.getBrand());
            stmt.setString(4, vehicle.getModel());
            stmt.setInt(5, vehicle.getYear());
            stmt.setDouble(6, vehicle.getRentalPricePerDay());
            stmt.setString(7, vehicle.getImageUrl());
            stmt.setInt(8, vehicle.getVehicleId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Thay đổi trạng thái xe (0, 1)
    public void toggleVehicleStatus(int vehicleId) {
        String sql = "UPDATE Vehicle SET Status = CASE WHEN Status = 0 THEN 1 ELSE 0 END WHERE VehicleId = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vehicle getVehicle(String vehicleId) throws SQLException {
        String sql = "SELECT * FROM Vehicle where VehicleId = ?";

        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vehicleId);

            try ( ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVehicleId(resultSet.getInt("VehicleId"));
                    vehicle.setVehicleTypeId(resultSet.getInt("VehicleTypeId"));
                    vehicle.setLicensePlate(resultSet.getString("LicensePlate"));
                    vehicle.setBrand(resultSet.getString("Brand"));
                    vehicle.setModel(resultSet.getString("Model"));
                    vehicle.setYear(resultSet.getInt("Year"));
                    vehicle.setRentalPricePerDay(resultSet.getDouble("RentalPricePerDay"));
                    vehicle.setStatus(resultSet.getInt("Status"));
                    vehicle.setImageUrl(resultSet.getString("ImageURL"));
                    return vehicle;
                }
            }
        }
        return null;
    }
}
