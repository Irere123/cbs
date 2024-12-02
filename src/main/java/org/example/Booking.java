package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private int bookingId;
    private int userId;
    private int cabId;
    private String pickupLocation;
    private String dropLocation;
    private String bookingStatus;
    private double fare;
    private Timestamp bookingTime;
    private Timestamp pickupTime;
    private Timestamp dropTime;


   /*
       ========== SETTERS AND GETTERS ========
    */

   public int getBookingId() {
       return bookingId;
   }

   public void setUserId(int userId) {
       this.userId = userId;
   }

   public void setCabId(int cabId) {
       this.cabId = cabId;
   }

   public void setPickupLocation(String pickupLocation) {
       this.pickupLocation = pickupLocation;
   }

   public void setDropLocation(String dropLocation) {
       this.dropLocation = dropLocation;
   }

   public void setBookingStatus(String bookingStatus) {
       this.bookingStatus = bookingStatus;
   }

   public void setFare(double fare) {
       this.fare = fare;
   }

   public void setBookingTime(Timestamp bookingTime) {
       this.bookingTime = bookingTime;
   }

   public void setPickupTime(Timestamp pickupTime) {
       this.pickupTime = pickupTime;
   }

   public void setDropTime(Timestamp dropTime) {
       this.dropTime = dropTime;
   }

    public double getFare() {
        return fare;
    }

    public Timestamp getBookingTime() {
       return bookingTime;
    }

    public Timestamp getPickupTime() {
       return pickupTime;
    }

    public Timestamp getDropTime() {
       return dropTime;
    }

    public int getCabId() {
        return cabId;
    }

    public String getPickupLocation() {
       return pickupLocation;
    }

    public String getDropLocation() {
       return dropLocation;
    }

    public String getBookingStatus() {
       return bookingStatus;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    /*
                       ========== DATABASE HELPERS ========
                    */
   public void saveBooking() throws Exception {
       String query = "INSERT INTO bookings(UserID, CabID, PickupLocation, DropLocation, BookingStatus, Fare, BookingTime, DropTime) VALUES(?,?,?,?,?,?,?,?, ?);";

       try (Connection connection =  Connector.getConnection(); PreparedStatement statement =  connection.prepareStatement(query)){
           statement.setInt(1, this.userId);
           statement.setInt(2, this.cabId);
           statement.setString(3, this.pickupLocation);
           statement.setString(4, this.dropLocation);
           statement.setString(5, this.bookingStatus);
           statement.setDouble(6, this.fare);
           statement.setTimestamp(7, this.bookingTime);
           statement.setTimestamp(8, this.pickupTime);
           statement.setTimestamp(9, this.dropTime);
           statement.executeUpdate();
       }
   }


   public static void deleteBooking(int bookingId) throws Exception {
       String query = "DELETE FROM bookings WHERE BookingID = ?";
       try (Connection connection =  Connector.getConnection(); PreparedStatement statement =  connection.prepareStatement(query)){
           statement.setInt(1, bookingId);
           statement.executeUpdate();
       }
   }


    public static Booking getBookingById(int bookingId) throws Exception {
        String query = "SELECT * FROM Bookings WHERE BookingID = ?";
        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bookingId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Booking booking = new Booking();
                booking.setBookingId(resultSet.getInt("BookingID"));
                booking.setUserId(resultSet.getInt("UserID"));
                booking.setCabId(resultSet.getInt("CabID"));
                booking.setPickupLocation(resultSet.getString("PickupLocation"));
                booking.setDropLocation(resultSet.getString("DropLocation"));
                booking.setBookingStatus(resultSet.getString("BookingStatus"));
                booking.setFare(resultSet.getDouble("Fare"));
                booking.setBookingTime(resultSet.getTimestamp("BookingTime"));
                booking.setPickupTime(resultSet.getTimestamp("PickupTime"));
                booking.setDropTime(resultSet.getTimestamp("DropTime"));
                return booking;
            }
        }
        return null;
    }


    public static List<Booking> getAllBookings() throws Exception {
        String query = "SELECT * FROM Bookings";
        List<Booking> bookings = new ArrayList<>();
        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Booking booking = new Booking();
                booking.setBookingId(resultSet.getInt("BookingID"));
                booking.setUserId(resultSet.getInt("UserID"));
                booking.setCabId(resultSet.getInt("CabID"));
                booking.setPickupLocation(resultSet.getString("PickupLocation"));
                booking.setDropLocation(resultSet.getString("DropLocation"));
                booking.setBookingStatus(resultSet.getString("BookingStatus"));
                booking.setFare(resultSet.getDouble("Fare"));
                booking.setBookingTime(resultSet.getTimestamp("BookingTime"));
                booking.setPickupTime(resultSet.getTimestamp("PickupTime"));
                booking.setDropTime(resultSet.getTimestamp("DropTime"));
                bookings.add(booking);
            }
        }
        return bookings;
    }


    public static void updateBooking(Booking booking) throws Exception {
        String query = "UPDATE Bookings SET UserID = ?, CabID = ?, PickupLocation = ?, DropLocation = ?, BookingStatus = ?, Fare = ?, BookingTime = ?, PickupTime = ?, DropTime = ? WHERE BookingID = ?";
        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, booking.getUserId());
            statement.setInt(2, booking.getCabId());
            statement.setString(3, booking.getPickupLocation());
            statement.setString(4, booking.getDropLocation());
            statement.setString(5, booking.getBookingStatus());
            statement.setDouble(6, booking.getFare());
            statement.setTimestamp(7, booking.getBookingTime());
            statement.setTimestamp(8, booking.getPickupTime());
            statement.setTimestamp(9, booking.getDropTime());
            statement.setInt(10, booking.getBookingId());
            statement.executeUpdate();
        }
    }
}
