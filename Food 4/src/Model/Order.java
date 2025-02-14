package Model;
import patterns.Observer;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private String foodName;
    private String status;
    private List<Observer> observers = new ArrayList<>();

    public Order(int orderId, String foodName, String status) {
        this.orderId = orderId;
        this.foodName = foodName;
        this.status = status;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Observer added: " + observer);
    }


    private void notifyObservers() {
        System.out.println("Notifying observers...");
        for (Observer observer : observers) {
            observer.update(status);
        }
    }
    public int getOrderId() {
        return orderId;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    @Override
    public String toString() {
        return String.format("Order ID: %d, Food: %s, Status: %s", orderId, foodName, status);
    }

}