package COMM;

public class Product {
    private int productId;
    private String name;
    private double price;
    private int stock;
    private LinkedList<Integer> reviews;
    
    public Product() {
        reviews = new LinkedList<>();
    }
    
    public Product(int productId, String name, double price, int stock) {
        this();
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    
    // Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    
    public LinkedList<Integer> getReviews() { return reviews; }
    
    public void addReview(int reviewId) {
        reviews.insert(reviewId);
    }
    
    public void addStock(int quantity) {
        this.stock += quantity;
    }
    
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", reviewsCount=" + reviews.size() +
                '}';
    }
}