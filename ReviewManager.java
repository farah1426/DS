package COMM;

import java.io.File;
import java.util.Scanner;

public class ReviewManager {
    public static Scanner userInput = new Scanner(System.in);
    public static LinkedList<Review> reviewCollection = new LinkedList<>();
    
    public LinkedList<Review> getreviewsData() {
        return reviewCollection;
    }
    
    public ReviewManager(String fileName) {
        try {
            File dataFile = new File(fileName);
            Scanner fileReader = new Scanner(dataFile);
            
            if (fileReader.hasNext()) {
                String header = fileReader.nextLine();
                System.out.println("⭐ Reading reviews header: " + header);
            }
            
            int loadedCount = 0;
            while(fileReader.hasNext()) {
                String dataLine = fileReader.nextLine();
                String[] reviewData = dataLine.split(",");
                
                if (reviewData.length >= 5) {
                    try {
                        int reviewId = Integer.parseInt(reviewData[0].trim());
                        int productId = Integer.parseInt(reviewData[1].trim());
                        int customerId = Integer.parseInt(reviewData[2].trim());
                        int rating = Integer.parseInt(reviewData[3].trim());
                        String comment = reviewData[4].trim();
                        
                        if (comment.startsWith("\"") && comment.endsWith("\"")) {
                            comment = comment.substring(1, comment.length() - 1);
                        }
                        
                        Review newReview = new Review(reviewId, productId, customerId, rating, comment);
                        reviewCollection.insert(newReview);
                        loadedCount++;
                    } catch (Exception e) {
                        System.out.println("️ Error parsing review: " + dataLine);
                    }
                }
            }
            fileReader.close();
            System.out.println(" Successfully loaded " + loadedCount + " reviews from CSV data");
        } catch (Exception error) {
            System.out.println(" Error reading reviews file: " + error.getMessage());
        }
    }
    
    public Review AddReview(int customerId, int productId) {
        System.out.print("Enter Review ID: ");//1
        int reviewId = userInput.nextInt();//2
        
        while (reviewIdExists(reviewId)) {//3
            System.out.print("Review ID already exists, enter different ID: ");//4
            reviewId = userInput.nextInt();//5
        }        
        
        System.out.print("Enter rating (1-5): ");//6
        int ratingValue = userInput.nextInt();//7
        while (ratingValue > 5 || ratingValue < 1) {//8
            System.out.print("Please enter valid rating (1-5): ");//9
            ratingValue = userInput.nextInt();//10
        }

        System.out.print("Enter comment: ");//11
        userInput.nextLine();//12
        String reviewComment = userInput.nextLine();//13

        Review newReview = new Review(reviewId, productId, customerId, ratingValue, reviewComment);//14
        reviewCollection.insert(newReview);//15
        return newReview;//16
    }

    public void updateReview() {
        if (reviewCollection.empty()) {//1
            System.out.println("No reviews available");//2
            return;//3
        }
        
        System.out.print("Enter review ID to update: ");//4
        int reviewId = userInput.nextInt();//5
        
        reviewCollection.findFirst();//6
        while (!reviewCollection.last()) {//7
            if (reviewCollection.retrieve().getReviewId() == reviewId) {//8
                Review review = reviewCollection.retrieve();//9
                reviewCollection.remove();//10
                
                System.out.println("1. Update Rating");//11
                System.out.println("2. Update Comment");//12
                System.out.print("Choose field to update: ");//13
                int choice = userInput.nextInt();//14
                
                switch(choice) {//15
                    case 1:
                        System.out.print("Enter new rating (1-5): ");//16
                        int newRating = userInput.nextInt();//17
                        while (newRating < 1 || newRating > 5) {//18
                            System.out.print("Please enter valid rating (1-5): ");//19
                            newRating = userInput.nextInt();//20
                        }
                        review.setRating(newRating);//21
                        break;//22
                    case 2:
                        System.out.print("Enter new comment: ");//23
                        userInput.nextLine();//24
                        String newComment = userInput.nextLine();//25
                        review.setComment(newComment);//26
                        break;//27
                    default:
                        System.out.println("Invalid choice");//28
                }
                
                reviewCollection.insert(review);//29
                System.out.println("Review updated successfully");//30
                return;
            }
            reviewCollection.findNext();//31
        }
        
        if (!reviewCollection.last() && reviewCollection.retrieve().getReviewId() == reviewId) {//32
            Review review = reviewCollection.retrieve();//33
            reviewCollection.remove();//34
            
            System.out.println("1. Update Rating");//35
            System.out.println("2. Update Comment");//36
            System.out.print("Choose field to update: ");//37
            int choice = userInput.nextInt();//38
            
            switch(choice) {//39
                case 1:
                    System.out.print("Enter new rating (1-5): ");//40
                    int newRating = userInput.nextInt();//41
                    while (newRating < 1 || newRating > 5) {//42
                        System.out.print("Please enter valid rating (1-5): ");//43
                        newRating = userInput.nextInt();//44
                    }
                    review.setRating(newRating);//45
                    break;//46
                case 2:
                    System.out.print("Enter new comment: ");//47
                    userInput.nextLine();//48
                    String newComment = userInput.nextLine();//49
                    review.setComment(newComment);//50
                    break;//51
                default:
                    System.out.println("Invalid choice");//52
            }
            
            reviewCollection.insert(review);///53
            System.out.println("Review updated successfully");//54
            return;//55
        }
        
        System.out.println("Review not found");//56
    }
   
    public boolean reviewIdExists(int reviewId) {
        if (reviewCollection.empty()) return false;
        
        reviewCollection.findFirst();
        while (!reviewCollection.last()) {
            if (reviewCollection.retrieve().getReviewId() == reviewId) {
                return true;
            }
            reviewCollection.findNext();
        }
        
        return !reviewCollection.last() && reviewCollection.retrieve().getReviewId() == reviewId;
    }
}