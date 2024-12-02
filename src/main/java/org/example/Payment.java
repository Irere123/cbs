package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Payment {
    private int paymentId;
    private int bookingId;
    private  double amount;
    private  String paymentMethod;
    private  String paymentStatus;
    private Timestamp paymentTime;


    /*
     ========== SETTERS AND GETTERS ========
  */
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Timestamp getPaymentTime() {
        return paymentTime;
    }
    public void setPaymentTime(Timestamp paymentTime) {
        this.paymentTime = paymentTime;
    }




    /*
     ========== DATABASE HELPERS ========
  */
    public void savePayment() throws Exception {
        String query = "INSERT INTO Payments (BookingID, Amount, PaymentMethod, PaymentStatus, PaymentTime) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, this.bookingId);
            statement.setDouble(2, this.amount);
            statement.setString(3, this.paymentMethod);
            statement.setString(4, this.paymentStatus);
            statement.setTimestamp(5, this.paymentTime);
            statement.executeUpdate();
        }
    }

    public static Payment getPaymentById(int paymentId) throws Exception {
        String query = "SELECT * FROM Payments WHERE PaymentID = ?";
        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, paymentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(resultSet.getInt("PaymentID"));
                payment.setBookingId(resultSet.getInt("BookingID"));
                payment.setAmount(resultSet.getDouble("Amount"));
                payment.setPaymentMethod(resultSet.getString("PaymentMethod"));
                payment.setPaymentStatus(resultSet.getString("PaymentStatus"));
                payment.setPaymentTime(resultSet.getTimestamp("PaymentTime"));
                return payment;
            }
        }
        return null;
    }

    public static List<Payment> getAllPayments() throws Exception {
        String query = "SELECT * FROM Payments";
        List<Payment> payments = new ArrayList<>();
        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(resultSet.getInt("PaymentID"));
                payment.setBookingId(resultSet.getInt("BookingID"));
                payment.setAmount(resultSet.getDouble("Amount"));
                payment.setPaymentMethod(resultSet.getString("PaymentMethod"));
                payment.setPaymentStatus(resultSet.getString("PaymentStatus"));
                payment.setPaymentTime(resultSet.getTimestamp("PaymentTime"));
                payments.add(payment);
            }
        }
        return payments;
    }


    public static void updatePayment(Payment payment) throws Exception {
        String query = "UPDATE Payments SET BookingID = ?, Amount = ?, PaymentMethod = ?, PaymentStatus = ?, PaymentTime = ? WHERE PaymentID = ?";
        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, payment.getBookingId());
            statement.setDouble(2, payment.getAmount());
            statement.setString(3, payment.getPaymentMethod());
            statement.setString(4, payment.getPaymentStatus());
            statement.setTimestamp(5, payment.getPaymentTime());
            statement.setInt(6, payment.getPaymentId());
            statement.executeUpdate();
        }
    }


    public static void deletePayment(int paymentId) throws Exception {
        String query = "DELETE FROM Payments WHERE PaymentID = ?";
        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, paymentId);
            statement.executeUpdate();
        }
    }
}
