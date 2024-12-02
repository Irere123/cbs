package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Cab {
    private int cabId;
    private String licensePlate;
    private  String driverName;
    private String driverPhoneNumber;
    private String cabType;
    private int capacity;
    private String currentLocation;
    private boolean availabilityStatus;

    /*
      ========== SETTERS AND GETTERS ========
   */

    public String getCabType() {
        return cabType;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDriverPhoneNumber() {
        return driverPhoneNumber;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public boolean getAvailabilityStatus() {
        return availabilityStatus;
    }

    public int getCabId() {
        return this.cabId;
    }

    public void setCabId(int cabId) {
        this.cabId = cabId;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setDriverPhoneNumber(String driverPhoneNumber) {
        this.driverPhoneNumber = driverPhoneNumber;
    }

    public void setCabType(String cabType) {
        this.cabType = cabType;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }


    /*
      ========== DATABASE HELPERS ========
   */
    public void saveCab() throws Exception {
        String query = "INSERT INTO Cabs (LicensePlate, DriverName, DriverPhoneNumber, CabType, Capacity, CurrentLocation, AvailabilityStatus) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, this.licensePlate);
            statement.setString(2, this.driverName);
            statement.setString(3, this.driverPhoneNumber);
            statement.setString(4, this.cabType);
            statement.setInt(5, this.capacity);
            statement.setString(6, this.currentLocation);
            statement.setBoolean(7, this.availabilityStatus);
            statement.executeUpdate();
        }
    }

    public static Cab getCabById(int cabId) throws Exception {
        String query = "SELECT * FROM Cabs WHERE CabID = ?";
        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cabId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Cab cab = new Cab();
                cab.setCabId(resultSet.getInt("CabID"));
                cab.setLicensePlate(resultSet.getString("LicensePlate"));
                cab.setDriverName(resultSet.getString("DriverName"));
                cab.setDriverPhoneNumber(resultSet.getString("DriverPhoneNumber"));
                cab.setCabType(resultSet.getString("CabType"));
                cab.setCapacity(resultSet.getInt("Capacity"));
                cab.setCurrentLocation(resultSet.getString("CurrentLocation"));
                cab.setAvailabilityStatus(resultSet.getBoolean("AvailabilityStatus"));
                return cab;
            }
        }
        return null;
    }


    public List<Cab> getAllCabs() throws Exception {
        String query = "SELECT * FROM Cabs";
        List<Cab> cabs = new ArrayList<>();
        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Cab cab = new Cab();
                cab.setCabId(resultSet.getInt("CabID"));
                cab.setLicensePlate(resultSet.getString("LicensePlate"));
                cab.setDriverName(resultSet.getString("DriverName"));
                cab.setDriverPhoneNumber(resultSet.getString("DriverPhoneNumber"));
                cab.setCabType(resultSet.getString("CabType"));
                cab.setCapacity(resultSet.getInt("Capacity"));
                cab.setCurrentLocation(resultSet.getString("CurrentLocation"));
                cab.setAvailabilityStatus(resultSet.getBoolean("AvailabilityStatus"));
                cabs.add(cab);
            }
        }
        return cabs;
    }


    public static void updateCab(Cab cab) throws Exception {
        String query = "UPDATE Cabs SET LicensePlate = ?, DriverName = ?, DriverPhoneNumber = ?, CabType = ?, Capacity = ?, CurrentLocation = ?, AvailabilityStatus = ? WHERE CabID = ?";
        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cab.getLicensePlate());
            statement.setString(2, cab.getDriverName());
            statement.setString(3, cab.getDriverPhoneNumber());
            statement.setString(4, cab.getCabType());
            statement.setInt(5, cab.getCapacity());
            statement.setString(6, cab.getCurrentLocation());
            statement.setBoolean(7, cab.getAvailabilityStatus());
            statement.setInt(8, cab.getCabId());
            statement.executeUpdate();
        }
    }
}
