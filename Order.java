package COMM;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Order {
    private int orderId;
    private int customerRefrence;
    private LinkedList<Integer> products;
    private double total_price;
    private LocalDate date;
    public String status;
    
    public Order() {
        products = new LinkedList<>();
    }
    
    public Order(int orderId, int customerRefrence, Integer[] productIds, double total_price, String date, String status) {
        this();
        this.orderId = orderId;
        this.customerRefrence = customerRefrence;
        this.total_price = total_price;
        this.status = status;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date, formatter);
        
        for (Integer productId : productIds) {
            products.insert(productId);
        }
    }
    
    // Getters and Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    
    public int getCustomerRefrence() { return customerRefrence; }
    public void setCustomerRefrence(int customerRefrence) { this.customerRefrence = customerRefrence; }
    
    public LinkedList<Integer> getProducts() { return products; }
    
    public double getTotal_price() { return total_price; }
    public void setTotal_price(double total_price) { this.total_price = total_price; }
    
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public void addProduct(int productId) {
        products.insert(productId);
    }
    
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerRefrence=" + customerRefrence +
                ", productsCount=" + products.size() +
                ", total_price=" + total_price +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}