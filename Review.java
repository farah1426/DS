package ds;


public class Review {
    private int reviewIdentifier;
    private int associatedProductId;
    private int reviewRating;
    private int associatedCustomerId;
    private String reviewComment;

    public Review(int id, int productId, int customerId, int rating, String comment) {
        this.reviewIdentifier = id;
        this.associatedProductId = productId;
        this.associatedCustomerId = customerId;
        this.reviewRating = rating;
        this.reviewComment = comment;
    }
    
    public void updateReviewDetails(Review updatedReview) {
        this.reviewIdentifier = updatedReview.reviewIdentifier;
        this.associatedProductId = updatedReview.associatedProductId;
        this.associatedCustomerId = updatedReview.associatedCustomerId;
        this.reviewRating = updatedReview.reviewRating;
        this.reviewComment = updatedReview.reviewComment;
    }
    
    public void modifyReview(int newRating, String newComment) { //مب من مصطفى، من ديب سيك
        this.reviewRating = newRating;
        this.reviewComment = newComment;
    }

    public int getReviewId() { return reviewIdentifier; }
    public int getProductId() { return associatedProductId; }
    public int getCustomerId() { return associatedCustomerId; }
    public int getRating() { return reviewRating; }
    public String getComment() { return reviewComment; }
    
    
    
    
    public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;}
	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;}
    


    public void showReviewDetails() {
        System.out.println("Review ID: " + reviewIdentifier);
        System.out.println("Product ID: " + associatedProductId);
        System.out.println("Customer ID: " + associatedCustomerId);
        System.out.println("Rating: " + reviewRating + "/5");
        System.out.println("Comment: " + reviewComment);
        System.out.println("---------------------------------");
    }
    
    public boolean isHighRating() { //مب من مصطفى، من ديب سيك
        return reviewRating >= 4;
    }
}
