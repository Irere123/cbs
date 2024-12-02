package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class User {
    private  int userId;
    private  String username;
    private String email;
    private String phoneNumber;
    private String passwordHash;


    /*
      ========== SETTERS AND GETTERS ========
   */
    public  void setUserId(int userId) {
        this.userId = userId;
    }

    public  void setUsername(String username) {
        this.username = username;
    }

    public  void setEmail(String email) {
        this.email = email;
    }

    public  void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public  void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public int getUserId() {
        return userId;
    }



    /*
      ========== DATABASE HELPERS ========
   */
    public void saveUser() throws  Exception{
        String query = "INSERT INTO Users (Name, Email, PhoneNumber, PasswordHash) VALUES (?, ?, ?, ?)";
        try (Connection connection  = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.email);
            preparedStatement.setString(3, this.phoneNumber);
            preparedStatement.setString(4, this.passwordHash);
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteUser(int userId) throws  Exception{
        String query = "DELETE FROM Users WHERE UserId = ?";
        try (Connection connection  = Connector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        }
    }

    public static  User getUserById(int userId) throws Exception {
        String query = "SELECT * FROM Users WHERE UserID = ?";
        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("UserID"));
                user.setUsername(resultSet.getString("Name"));
                user.setEmail(resultSet.getString("Email"));
                user.setPhoneNumber(resultSet.getString("PhoneNumber"));
                user.setPasswordHash(resultSet.getString("PasswordHash"));
                return user;
            }
        }
        return null;
    }

    public static List<User> getAllUsers() throws Exception {
        String query = "SELECT * FROM Users";
        List<User> users = new ArrayList<>();
        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("UserID"));
                user.setUsername(resultSet.getString("Name"));
                user.setEmail(resultSet.getString("Email"));
                user.setPhoneNumber(resultSet.getString("PhoneNumber"));
                user.setPasswordHash(resultSet.getString("PasswordHash"));
                users.add(user);
            }
        }
        return users;
    }

    public static void updateUser(User user) throws Exception {
        String query = "UPDATE Users SET Name = ?, Email = ?, PhoneNumber = ?, PasswordHash = ? WHERE UserID = ?";
        try (Connection connection = Connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getPasswordHash());
            statement.setInt(5, user.getUserId());
            statement.executeUpdate();
        }
    }
}
