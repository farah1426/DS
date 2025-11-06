package ds;

public class Product {
    private int productIdentifier;
    private String productName;
    private double productPrice;
    private int stockQuantity;
    private LinkedList<Review> productReviews;
    
    public Product(int id, String name, double price, int stock) {
        this.productIdentifier = id;
        this.productName = name;
        this.productPrice = price;
        this.stockQuantity = stock;
        this.productReviews = new LinkedList<>();
    }
    
    public void modifyProduct(Product updatedProduct) {
        this.productIdentifier = updatedProduct.productIdentifier;
        this.productName = updatedProduct.productName;
        this.productPrice = updatedProduct.productPrice;
        this.stockQuantity = updatedProduct.stockQuantity;
        this.productReviews = updatedProduct.productReviews;
    }

    public int getProductId() { return productIdentifier; }
    public String getName() { return productName; }
    public double getPrice() { return productPrice; }
    public int getStock() { return stockQuantity; }
    //public LinkedList<Review> getReviews() { return productReviews; } خرابيط احس

    public void setPrice(double newPrice) { this.productPrice = newPrice; }
    public void setStock(int newStock) { this.stockQuantity = newStock; }
    //public void increaseStock(int quantity) { this.stockQuantity += quantity; }
    //public void decreaseStock(int quantity) { this.stockQuantity -= quantity; }

    public void addProductReview(Review newReview) {        
        productReviews.insert(newReview);
    }
    
    public boolean removeProductReview(int reviewId) {// من ذيب سيك وماشفتها مطلوبه بالدوك ومصطفى ماسواها
        if (productReviews.empty()) return false;
        
        productReviews.findFirst();
        while (true) {
            Review current = productReviews.retrieve();
            if (current.getReviewId() == reviewId) {
                productReviews.remove();
                return true;
            }
            if (productReviews.last()) break;
            productReviews.findNext();
        }
        return false;
    }

    public double calculateAverageRating() {// يبي لي اتاكد من صحة اللوجك، مطلوبه لكن الحل غير مصطفى
        if (productReviews.empty()) return 0.0;
        
        productReviews.findFirst();
        double totalRating = 0;
        int reviewCount = 0;
        
        while (true) {
            totalRating += productReviews.retrieve().getRating();
            reviewCount++;
            if (productReviews.last()) break;
            productReviews.findNext();
        }
        return totalRating / reviewCount;
    }
    
    public LinkedList<Review> getReviewsByCustomer(int customerId) {// يبي لي اتاكد من صحة اللوجك، مطلوبه لكن الحل غير مصطفى
        LinkedList<Review> customerReviews = new LinkedList<>();
        
        if (!productReviews.empty()) {
            productReviews.findFirst();
            while (true) {
                Review current = productReviews.retrieve();
                if (current.getCustomerId() == customerId) {
                    customerReviews.insert(current);
                }
                if (productReviews.last()) break;
                productReviews.findNext();
            }
        }
        return customerReviews;
    }

    public void showAllReviews() {
        System.out.println("=== Reviews for " + productName + " ===");
        if (productReviews.empty()) {
            System.out.println("  No reviews available.");
        } else {
            productReviews.findFirst();
            while (true) {
                productReviews.retrieve().showReviewDetails();
                if (productReviews.last()) break;
                productReviews.findNext();
            }
        }
        System.out.println("================================");
    }

    public void showProductDetails() {
        System.out.println("Product ID: " + productIdentifier);
        System.out.println("Name: " + productName);
        System.out.println("Price: $" + productPrice);
        System.out.println("Stock Available: " + stockQuantity);
        System.out.println("Average Rating: " + String.format("%.2f", calculateAverageRating()));
        System.out.println("Total Reviews: " + (productReviews.empty() ? 0 : getReviewCount()));
        System.out.println("---------------------------------");
    }
    
    private int getReviewCount() {
        int count = 0;
        if (!productReviews.empty()) {
            productReviews.findFirst();
            while (true) {
                count++;
                if (productReviews.last()) break;
                productReviews.findNext();
            }
        }
        return count;
    }
    
    public boolean isOutOfStock() {
        return stockQuantity <= 0;
    }
}
