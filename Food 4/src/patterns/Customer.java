package patterns;

public class Customer implements Observer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void update(String status) {
        System.out.println("Update method called for customer: " + name);
        System.out.println(name + ", your order status is: " + status);
    }
}