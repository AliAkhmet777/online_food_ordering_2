package Service;

import DAO.DatabaseConnection;
import Model.Order;

import Model.Order;
import patterns.Customer;
import DAO.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderService {

    public void placeOrder(String foodName, Customer customer) {
        String sql = "INSERT INTO orders (food_name, status) VALUES (?, 'Preparing')";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, foodName);
            stmt.executeUpdate();
            System.out.println("Order placed successfully!");

            // Create an order object and add the customer as an observer
            Order order = new Order(1, foodName, "Preparing");
            order.addObserver(customer);
        } catch (SQLException e) {
            System.err.println("Error placing order: " + e.getMessage());
        }
    }

    public void trackOrder(int orderId) {
        String sql = "SELECT * FROM orders WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String foodName = rs.getString("food_name");
                String status = rs.getString("status");
                System.out.println(new Order(orderId, foodName, status));
            } else {
                System.out.println("Order not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error tracking order: " + e.getMessage());
        }
    }

    public void updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, orderId);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Order status updated to: " + status);
            } else {
                System.out.println("Order not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating order status: " + e.getMessage());
        }
    }

    public void deleteTicket(int orderId) {
        String sql = "DELETE FROM orders WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Order deleted successfully.");
            } else {
                System.out.println("Order not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting order: " + e.getMessage());
        }
    }
}