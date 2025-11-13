package COMM;

public class Customer {
    private int customerId;
    private String name;
    private String email;
    private LinkedList<Integer> orders;

    public Customer() {
        orders = new LinkedList<>();
    }

    public Customer(int customerId, String name, String email) {
        this();
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public LinkedList<Integer> getOrders() { return orders; }
    
    public void addOrder(int orderId) {
        orders.insert(orderId);
    }
    
    public void removeOrder(int orderId) {
        orders.findFirst();
        while (!orders.last()) {
            if (orders.retrieve() == orderId) {
                orders.remove();
                return;
            }
            orders.findNext();
        }
        if (!orders.last() && orders.retrieve() == orderId) {
            orders.remove();
        }
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", ordersCount=" + orders.size() +
                '}';
    }
}