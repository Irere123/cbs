package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== User Management System ====");
            System.out.println("1. Add User");
            System.out.println("2. View All Users");
            System.out.println("3. Get User by ID");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        addUser();
                        break;
                    case 2:
                        viewAllUsers();
                        break;
                    case 3:
                        getUserById();
                        break;
                    case 4:
                        updateUser();
                        break;
                    case 5:
                        deleteUser();
                        break;
                    case 6:
                        System.out.println("Exiting the system. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }


    private static void addUser() throws Exception {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPhoneNumber(phone);
        user.setPasswordHash(password);
        user.saveUser();
        System.out.println("User added successfully!");
    }

    private static void viewAllUsers() throws Exception {
        List<User> users = User.getAllUsers();
        System.out.println("===== All Users ===");
        for (User user : users) {
            System.out.println("ID: " + user.getUserId());
            System.out.println("Name: " + user.getUsername());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Phone: " + user.getPhoneNumber());
            System.out.println("Password: " + user.getPasswordHash());
            System.out.println("========");
        }
    }

    private static void getUserById() throws Exception {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        User user = User.getUserById(userId);
        if (user != null) {
            System.out.println("ID: " + user.getUserId());
            System.out.println("Name: " + user.getUsername());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Phone: " + user.getPhoneNumber());
            System.out.println("Password: " + user.getPasswordHash());
        } else {
            System.out.println("User not found.");
        }
    }

    private static void updateUser() throws Exception {
        System.out.print("Enter user ID to update: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter updated name: ");
        String name = scanner.nextLine();
        System.out.print("Enter updated email: ");
        String email = scanner.nextLine();
        System.out.print("Enter updated phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter updated password: ");
        String password = scanner.nextLine();

        User user = new User();
        user.setUserId(userId);
        user.setUsername(name);
        user.setEmail(email);
        user.setPhoneNumber(phone);
        user.setPasswordHash(password);
        User.updateUser(user);
        System.out.println("User updated successfully!");
    }

    private static void deleteUser() throws Exception {
        System.out.print("Enter user ID to delete: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        User.deleteUser(userId);
        System.out.println("User deleted successfully!");
    }
}