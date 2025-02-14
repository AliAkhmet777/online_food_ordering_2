package Service;

import DAO.DatabaseConnection;
import Model.FoodItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuService {
    public List<FoodItem> getMenu() {
        List<FoodItem> menu = new ArrayList<>();
        String sql = "SELECT * FROM food_items";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                menu.add(new FoodItem(id, name, price));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching menu: " + e.getMessage());
        }

        return menu;
    }

    public void displayMenu() {
        List<FoodItem> menu = getMenu();
        System.out.println("\n--- Menu ---");
        for (FoodItem item : menu) {
            System.out.println(item);
        }
    }

    public FoodItem getFoodItemById(int id) {
        String sql = "SELECT * FROM food_items WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                return new FoodItem(id, name, price);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching food item: " + e.getMessage());
        }

        return null;
    }
}