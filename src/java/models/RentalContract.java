/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author SHD
 */
public class RentalContract {

    private int contractId;
    private int userId;
    private Date expectedPickupDateTime;
    private Date expectedReturnDateTime;
    private double depositAmount;
    private double totalAmount;
    private int status;

    public String getFormattedPickupDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(expectedPickupDateTime);
    }

    public String getFormattedReturnDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(expectedReturnDateTime);
    }

    public long getDifferenceInDays() {
        // Get the time in milliseconds
        long pickupTime = expectedPickupDateTime.getTime();
        long returnTime = expectedReturnDateTime.getTime();

        // Calculate the difference in milliseconds
        long differenceInMillis = returnTime - pickupTime;

        // Convert milliseconds to days
        long differenceInDays = differenceInMillis / (1000 * 60 * 60 * 24);

        return differenceInDays;
    }

    public RentalContract() {
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getExpectedPickupDateTime() {
        return expectedPickupDateTime;
    }

    public void setExpectedPickupDateTime(Date expectedPickupDateTime) {
        this.expectedPickupDateTime = expectedPickupDateTime;
    }

    public Date getExpectedReturnDateTime() {
        return expectedReturnDateTime;
    }

    public void setExpectedReturnDateTime(Date expectedReturnDateTime) {
        this.expectedReturnDateTime = expectedReturnDateTime;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
