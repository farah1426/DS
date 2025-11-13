package COMM;

public class Review {
    private int reviewId;
    private int product;
    private int customer;
    private int rating;
    private String comment;
    
    public Review() {}
    
    public Review(int reviewId, int product, int customer, int rating, String comment) {
        this.reviewId = reviewId;
        this.product = product;
        this.customer = customer;
        this.rating = rating;
        this.comment = comment;
    }
    
    // Getters and Setters
    public int getReviewId() { return reviewId; }
    public void setReviewId(int reviewId) { this.reviewId = reviewId; }
    
    public int getProduct() { return product; }
    public void setProduct(int product) { this.product = product; }
    
    public int getCustomer() { return customer; }
    public void setCustomer(int customer) { this.customer = customer; }
    
    public int getRating() { return rating; }
    public void setRating(int rating) { 
        if (rating >= 1 && rating <= 5) {
            this.rating = rating;
        }
    }
    
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    
    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", product=" + product +
                ", customer=" + customer +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}