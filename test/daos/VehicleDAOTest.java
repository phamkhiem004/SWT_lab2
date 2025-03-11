/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package daos;

import models.Vehicle;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.sql.Connection;

/**
 *
 * @author Acer Predator
 */
public class VehicleDAOTest {

    public VehicleDAOTest() {
    }

    @Test
    public void testGetAllVehicleTypes() throws Exception {
    }

    @Test
    public void testGetAvailableVehicles() throws Exception {
    }

    @Test
    public void testGetAllVehicles() throws Exception {
    }

    @Test
    public void testGetVehicleTypes() {
    }

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @InjectMocks
    private VehicleDAO vehicleDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    }

    @Test
    public void testCreateVehicle_Success() throws Exception {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleTypeId(1);
        vehicle.setLicensePlate("ABC-123");
        vehicle.setBrand("Toyota");
        vehicle.setModel("Corolla");
        vehicle.setYear(2022);
        vehicle.setRentalPricePerDay(50.0);
        vehicle.setImageUrl("http://example.com/image.jpg");

        // Act
        vehicleDAO.createVehicle(vehicle);

        // Assert - Verify đúng số lần gọi các phương thức
        verify(mockConnection).prepareStatement(anyString());
        verify(mockPreparedStatement).setInt(1, vehicle.getVehicleTypeId());
        verify(mockPreparedStatement).setString(2, vehicle.getLicensePlate());
        verify(mockPreparedStatement).setString(3, vehicle.getBrand());
        verify(mockPreparedStatement).setString(4, vehicle.getModel());
        verify(mockPreparedStatement).setInt(5, vehicle.getYear());
        verify(mockPreparedStatement).setDouble(6, vehicle.getRentalPricePerDay());
        verify(mockPreparedStatement).setString(7, vehicle.getImageUrl());
        verify(mockPreparedStatement).executeUpdate();
        verify(mockPreparedStatement).close();
    }

    @Test
    public void testUpdateVehicle() {
    }

    @Test
    public void testToggleVehicleStatus() {
    }

    @Test
    public void testGetVehicle() throws Exception {
    }

}
