

import Service.MenuService;
import Service.OrderService;
import patterns.Customer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MenuService menuService = new MenuService();
        OrderService orderService = new OrderService();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        Customer customer = new Customer(customerName);

        while (true) {
            System.out.println("\n--- Online Food Ordering System ---");
            System.out.println("1. Menu Browsing");
            System.out.println("2. Order Placement");
            System.out.println("3. Delivery Tracking");
            System.out.println("4. Update Order Status");
            System.out.println("5. Delete Order");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Menu Browsing
                    menuService.displayMenu();
                    break;
                case 2:
                    // Order Placement
                    menuService.displayMenu();
                    System.out.print("Enter the ID of the food item you want to order: ");
                    int foodId = scanner.nextInt();
                    String foodName = menuService.getFoodItemById(foodId).getName();
                    orderService.placeOrder(foodName, customer);
                    break;
                case 3:
                    // Delivery Tracking
                    System.out.print("Enter your Order ID: ");
                    int orderId = scanner.nextInt();
                    orderService.trackOrder(orderId);
                    break;
                case 4:
                    // Update Order Status
                    System.out.print("Enter the Order ID to update: ");
                    int updateOrderId = scanner.nextInt();
                    System.out.print("Enter the new status (e.g., Preparing, Out for Delivery, Delivered): ");
                    String newStatus = scanner.next();
                    orderService.updateOrderStatus(updateOrderId, newStatus);
                    break;
                case 5:
                    // Delete Order
                    System.out.print("Enter the Order ID to delete: ");
                    int deleteOrderId = scanner.nextInt();
                    orderService.deleteTicket(deleteOrderId);
                    break;
                case 6:
                    // Exit
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}