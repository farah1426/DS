package COMM;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    
    public static Scanner input = new Scanner(System.in);
    
    public static ProductManager pdata = new ProductManager("products.csv");
    public static LinkedList<Product> products;
    
    public static CustomerManager cdata = new CustomerManager("customers.csv");
    public static LinkedList<Customer> customers;
    
    public static OrderManager odata = new OrderManager("orders.csv");
    public static LinkedList<Order> orders;
    
    public static ReviewManager rdata = new ReviewManager("reviews.csv");
    public static LinkedList<Review> reviews;
    
    public static void loadData() {
        System.out.println("LOADING THE CSV DATA...");
        
        products = pdata.getproductsData();
        customers = cdata.getcustomersData();
        orders = odata.getordersData();
        reviews = rdata.getreviewsData();
        
        System.out.println("\nLOADING SUMMARY:");
        System.out.println(" Products: " + products.size() + " items");
        System.out.println(" Customers: " + customers.size() + " customers");
        System.out.println(" Orders: " + orders.size() + " orders");
        System.out.println(" Reviews: " + reviews.size() + " reviews");
        
        linkOrdersToCustomers();
        linkReviewsToProducts();
        
        System.out.println("\nALL YOUR DATA LOADED SUCCESSFULLY!");
    }
    
    private static void linkOrdersToCustomers() {
        if (customers.empty() || orders.empty()) return;
        
        customers.findFirst();
        while (!customers.last()) {
            Customer customer = customers.retrieve();
            orders.findFirst();
            while (!orders.last()) {
                Order order = orders.retrieve();
                if (customer.getCustomerId() == order.getCustomerRefrence()) {
                    customer.addOrder(order.getOrderId());
                }
                orders.findNext();
            }
            if (!orders.last()) {
                Order order = orders.retrieve();
                if (customer.getCustomerId() == order.getCustomerRefrence()) {
                    customer.addOrder(order.getOrderId());
                }
            }
            customers.findNext();
        }
        if (!customers.last()) {
            Customer customer = customers.retrieve();
            orders.findFirst();
            while (!orders.last()) {
                Order order = orders.retrieve();
                if (customer.getCustomerId() == order.getCustomerRefrence()) {
                    customer.addOrder(order.getOrderId());
                }
                orders.findNext();
            }
            if (!orders.last()) {
                Order order = orders.retrieve();
                if (customer.getCustomerId() == order.getCustomerRefrence()) {
                    customer.addOrder(order.getOrderId());
                }
            }
        }
    }
    
    private static void linkReviewsToProducts() {
        if (products.empty() || reviews.empty()) return;
        
        products.findFirst();
        while (!products.last()) {
            Product product = products.retrieve();
            reviews.findFirst();
            while (!reviews.last()) {
                Review review = reviews.retrieve();
                if (product.getProductId() == review.getProduct()) {
                    product.addReview(review.getReviewId());
                }
                reviews.findNext();
            }
            if (!reviews.last()) {
                Review review = reviews.retrieve();
                if (product.getProductId() == review.getProduct()) {
                    product.addReview(review.getReviewId());
                }
            }
            products.findNext();
        }
        if (!products.last()) {
            Product product = products.retrieve();
            reviews.findFirst();
            while (!reviews.last()) {
                Review review = reviews.retrieve();
                if (product.getProductId() == review.getProduct()) {
                    product.addReview(review.getReviewId());
                }
                reviews.findNext();
            }
            if (!reviews.last()) {
                Review review = reviews.retrieve();
                if (product.getProductId() == review.getProduct()) {
                    product.addReview(review.getReviewId());
                }
            }
        }
    }
    
    public static int mainMenu() {
        System.out.println("\n=== E-COMMERCE INVENTORY MANAGEMENT SYSTEM ===");
        System.out.println("1. Products Management");
        System.out.println("2. Customers Management");
        System.out.println("3. Orders Management");
        System.out.println("4. Reviews Management");
        System.out.println("5. Advanced Queries");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        return input.nextInt();
    }
    
    public static void productsMenu() {
        int choice;
        do {
            System.out.println("\n--- PRODUCTS MANAGEMENT ---");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Update Product");
            System.out.println("4. Search Product by ID");
            System.out.println("5. Search Product by Name");
            System.out.println("6. View Out-of-Stock Products");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            
            switch(choice) {
                case 1:
                    pdata.addProduct();
                    break;
                case 2:
                    Product removed = pdata.removeProduct();
                    if(removed != null) {
                        System.out.println("Product removed: " + removed.getName());
                    }
                    break;
                case 3:
                    pdata.updateProduct();
                    break;
                case 4:
                    Product foundById = pdata.searchProducID();
                    if(foundById != null) {
                        System.out.println("Product found: " + foundById);
                    }
                    break;
                case 5:
                    Product foundByName = pdata.searchProducName();
                    if(foundByName != null) {
                        System.out.println("Product found: " + foundByName);
                    }
                    break;
                case 6:
                    pdata.Out_Stock_Products();
                    break;
                case 7:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while(choice != 7);
    }
    
    public static void customersMenu() {
        int choice;
        do {
            System.out.println("\n--- CUSTOMERS MANAGEMENT ---");
            System.out.println("1. Register New Customer");
            System.out.println("2. Place New Order");
            System.out.println("3. View Order History");
            System.out.println("4. View Customer Reviews");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            
            switch(choice) {
                case 1:
                    cdata.RegisterCustomer();
                    break;
                case 2:
                    placeOrderForCustomer(); // تتحقق من وجود العميل
                    break;
                case 3:
                    cdata.OrderHistory();
                    break;
                case 4:
                    extractCustomerReviews();
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while(choice != 5);
    }
    
    public static void ordersMenu() {
        int choice;
        do {
            System.out.println("\n--- ORDERS MANAGEMENT ---");
            System.out.println("1. Place New Order");
            System.out.println("2. Cancel Order");
            System.out.println("3. Update Order Status");
            System.out.println("4. Search Order by ID");
            System.out.println("5. View Orders Between Dates");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            
            switch(choice) {
                case 1:
                    placeOrder(); // ما تتحقق من وجود العميل
                    break;
                case 2:
                    cancelOrder();
                    break;
                case 3:
                    System.out.print("Enter order ID to update: ");
                    int orderId = input.nextInt();
                    odata.UpdateOrder(orderId);
                    break;
                case 4:
                    System.out.print("Enter order ID to search: ");
                    Order foundOrder = odata.searchOrderID(input.nextInt());
                    if(foundOrder != null) {
                        System.out.println("Order found: " + foundOrder);
                    }
                    break;
                case 5:
                    viewOrdersBetweenDates();
                    break;
                case 6:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while(choice != 6);
    }
    
    public static void reviewsMenu() {
        int choice;
        do {
            System.out.println("\n--- REVIEWS MANAGEMENT ---");
            System.out.println("1. Add Review");
            System.out.println("2. Edit Review");
            System.out.println("3. Get Average Rating for Product");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            
            switch(choice) {
                case 1:
                    addNewReview();
                    break;
                case 2:
                    rdata.updateReview();
                    break;
                case 3:
                    getAverageRating();
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while(choice != 4);
    }
    
    public static void advancedQueriesMenu() {
        int choice;
        do {
            System.out.println("\n--- ADVANCED QUERIES ---");
            System.out.println("1. Top 3 Products by Rating");
            System.out.println("2. Common Highly-Rated Products Between Customers");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            
            switch(choice) {
                case 1:
                    showTop3Products();
                    break;
                case 2:
                    showCommonHighlyRatedProducts();
                    break;
                case 3:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while(choice != 3);
    }
    
    // Method للـ Customers Menu - تتحقق من وجود العميل
    public static void placeOrderForCustomer() {
        Order newOrder = new Order();
        
        System.out.print("Enter order ID: ");
        int orderId = input.nextInt();
        while(odata.checkOrderID(orderId)) {
            System.out.print("Order ID already exists. Enter new order ID: ");
            orderId = input.nextInt();
        }
        newOrder.setOrderId(orderId);
        
        System.out.print("Enter customer ID: ");
        int customerId = input.nextInt();
        
        if(!cdata.checkCustomerID(customerId)) {
            System.out.println("Customer ID not found. Cannot place order.");
            return;
        }
        
        newOrder.setCustomerRefrence(customerId);
        
        LinkedList<Integer> orderProducts = new LinkedList<>();
        double totalPrice = 0;
        char addMore = 'y';
        
        while(addMore == 'y' || addMore == 'Y') {
            System.out.print("Enter product ID: ");
            int productId = input.nextInt();
            
            Product product = pdata.getProductData(productId);
            if(product != null && product.getStock() > 0) {
                orderProducts.insert(productId);
                totalPrice += product.getPrice();
                
                product.setStock(product.getStock() - 1);
                System.out.println("Product added to order. Remaining stock: " + product.getStock());
            } else {
                System.out.println("Product not available or out of stock.");
            }
            
            System.out.print("Add another product? (y/n): ");
            addMore = input.next().charAt(0);
        }
        
        if(orderProducts.empty()) {
            System.out.println("No products added. Order cancelled.");
            return;
        }
        
        newOrder.setTotal_price(totalPrice);
        
        System.out.print("Enter order date (yyyy-MM-dd): ");
        String dateStr = input.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate orderDate = LocalDate.parse(dateStr, formatter);
        newOrder.setDate(orderDate);
        
        System.out.print("Enter order status (Pending/Shipped/Delivered/Cancelled): ");
        newOrder.setStatus(input.next());
        
        orderProducts.findFirst();
        while(!orderProducts.last()) {
            newOrder.addProduct(orderProducts.retrieve());
            orderProducts.findNext();
        }
        if (!orderProducts.last()) {
            newOrder.addProduct(orderProducts.retrieve());
        }
        
        orders.insert(newOrder);
        
        customers.findFirst();
        while(!customers.last()) {
            if(customers.retrieve().getCustomerId() == customerId) {
                customers.retrieve().addOrder(orderId);
                break;
            }
            customers.findNext();
        }
        if (!customers.last() && customers.retrieve().getCustomerId() == customerId) {
            customers.retrieve().addOrder(orderId);
        }
        
        System.out.println("Order placed successfully! Total: $" + totalPrice);
    }
    
    // Method للـ Orders Menu - ما تتحقق من وجود العميل
    public static void placeOrder() {
        Order newOrder = new Order();
        
        System.out.print("Enter order ID: ");
        int orderId = input.nextInt();
        while(odata.checkOrderID(orderId)) {
            System.out.print("Order ID already exists. Enter new order ID: ");
            orderId = input.nextInt();
        }
        newOrder.setOrderId(orderId);
        
        System.out.print("Enter customer ID: ");
        int customerId = input.nextInt();
        newOrder.setCustomerRefrence(customerId);
        
        LinkedList<Integer> orderProducts = new LinkedList<>();
        double totalPrice = 0;
        char addMore = 'y';
        
        while(addMore == 'y' || addMore == 'Y') {
            System.out.print("Enter product ID: ");
            int productId = input.nextInt();
            
            Product product = pdata.getProductData(productId);
            if(product != null && product.getStock() > 0) {
                orderProducts.insert(productId);
                totalPrice += product.getPrice();
                
                product.setStock(product.getStock() - 1);
                System.out.println("Product added to order. Remaining stock: " + product.getStock());
            } else {
                System.out.println("Product not available or out of stock.");
            }
            
            System.out.print("Add another product? (y/n): ");
            addMore = input.next().charAt(0);
        }
        
        if(orderProducts.empty()) {
            System.out.println("No products added. Order cancelled.");
            return;
        }
        
        newOrder.setTotal_price(totalPrice);
        
        System.out.print("Enter order date (yyyy-MM-dd): ");
        String dateStr = input.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate orderDate = LocalDate.parse(dateStr, formatter);
        newOrder.setDate(orderDate);
        
        System.out.print("Enter order status (Pending/Shipped/Delivered/Cancelled): ");
        newOrder.setStatus(input.next());
        
        orderProducts.findFirst();
        while(!orderProducts.last()) {
            newOrder.addProduct(orderProducts.retrieve());
            orderProducts.findNext();
        }
        if (!orderProducts.last()) {
            newOrder.addProduct(orderProducts.retrieve());
        }
        
        orders.insert(newOrder);
        
        customers.findFirst();
        while(!customers.last()) {
            if(customers.retrieve().getCustomerId() == customerId) {
                customers.retrieve().addOrder(orderId);
                break;
            }
            customers.findNext();
        }
        if (!customers.last() && customers.retrieve().getCustomerId() == customerId) {
            customers.retrieve().addOrder(orderId);
        }
        
        System.out.println("Order placed successfully! Total: $" + totalPrice);
    }
    
    public static void cancelOrder() {
        System.out.print("Enter order ID to cancel: ");
        int orderId = input.nextInt();
        
        int result = odata.cancelOrder(orderId);
        if(result == 1) {
            Order cancelledOrder = odata.searchOrderID(orderId);
            if(cancelledOrder != null) {
                cancelledOrder.getProducts().findFirst();
                while(!cancelledOrder.getProducts().last()) {
                    int productId = cancelledOrder.getProducts().retrieve();
                    Product product = pdata.getProductData(productId);
                    if(product != null) {
                        product.setStock(product.getStock() + 1);
                    }
                    cancelledOrder.getProducts().findNext();
                }
                if (!cancelledOrder.getProducts().last()) {
                    int productId = cancelledOrder.getProducts().retrieve();
                    Product product = pdata.getProductData(productId);
                    if(product != null) {
                        product.setStock(product.getStock() + 1);
                    }
                }
            }
            System.out.println("Order cancelled successfully.");
        }
    }
    
    public static void viewOrdersBetweenDates() {
        System.out.print("Enter start date (yyyy-MM-dd): "); 
        String startDate = input.next();
        
        System.out.print("Enter end date (yyyy-MM-dd): "); 
        String endDate = input.next();
        
        odata.BetweenTwoDates(startDate, endDate);
    }
    
    public static void addNewReview() {
        System.out.print("Enter customer ID: ");
        int customerId = input.nextInt();
        if(!cdata.checkCustomerID(customerId)) {
            System.out.println("Customer ID not found.");
            return;
        }
        
        System.out.print("Enter product ID: ");
        int productId = input.nextInt();
        if(!pdata.checkProductID(productId)) {
            System.out.println("Product ID not found.");
            return;
        }
        
        Review newReview = rdata.AddReview(customerId, productId);
        System.out.println("Review added successfully: " + newReview);
        
        products.findFirst();
        while(!products.last()) {
            if(products.retrieve().getProductId() == productId) {
                products.retrieve().addReview(newReview.getReviewId());
                break;
            }
            products.findNext();
        }
        if (!products.last() && products.retrieve().getProductId() == productId) {
            products.retrieve().addReview(newReview.getReviewId());
        }
    }
    
    public static void getAverageRating() {
        System.out.print("Enter product ID: ");
        int productId = input.nextInt();
        
        float avgRating = calculateAverageRating(productId);
        if(avgRating >= 0) {
            System.out.println("Average rating for product " + productId + ": " + avgRating);
        } else {
            System.out.println("No reviews found for this product.");
        }
    }
    
    public static void extractCustomerReviews() {
        System.out.print("Enter customer ID: ");
        int customerId = input.nextInt();
        
        LinkedList<Review> customerReviews = new LinkedList<>();
        reviews.findFirst();
        while(!reviews.last()) {
            if(reviews.retrieve().getCustomer() == customerId) {
                customerReviews.insert(reviews.retrieve());
            }
            reviews.findNext();
        }
        if (!reviews.last() && reviews.retrieve().getCustomer() == customerId) {
            customerReviews.insert(reviews.retrieve());
        }
        
        if(customerReviews.empty()) {
            System.out.println("No reviews found for customer " + customerId);
        } else {
            System.out.println("Reviews by customer " + customerId + ":");
            customerReviews.findFirst();
            while(!customerReviews.last()) {
                Review review = customerReviews.retrieve();
                Product product = pdata.getProductData(review.getProduct());
                System.out.println("Product: " + (product != null ? product.getName() : "Unknown") + 
                                 ", Rating: " + review.getRating() + 
                                 ", Comment: " + review.getComment());
                customerReviews.findNext();
            }
            if (!customerReviews.last()) {
                Review review = customerReviews.retrieve();
                Product product = pdata.getProductData(review.getProduct());
                System.out.println("Product: " + (product != null ? product.getName() : "Unknown") + 
                                 ", Rating: " + review.getRating() + 
                                 ", Comment: " + review.getComment());
            }
        }
    }
    
    public static void showTop3Products() {
        LinkedPQ<Product> topProducts = new LinkedPQ<>();
        
        products.findFirst();
        while(!products.last()) {
            Product product = products.retrieve();
            float avgRating = calculateAverageRating(product.getProductId());
            if(avgRating > 0) {
                topProducts.enqueue(product, avgRating);
            }
            products.findNext();
        }
        if (!products.last()) {
            Product product = products.retrieve();
            float avgRating = calculateAverageRating(product.getProductId());
            if(avgRating > 0) {
                topProducts.enqueue(product, avgRating);
            }
        }
        
        System.out.println("Top 3 Products by Average Rating:");
        for(int i = 1; i <= 3 && !topProducts.empty(); i++) {
            PQElement<Product> topProduct = topProducts.serve();
            System.out.println(i + ". " + topProduct.data.getName() + 
                             " - Rating: " + (topProduct.priority) +
                             " - Price: $" + topProduct.data.getPrice());
        }
    }
    
    public static void showCommonHighlyRatedProducts() {
        System.out.print("Enter first customer ID: ");
        int customer1 = input.nextInt();
        System.out.print("Enter second customer ID: ");
        int customer2 = input.nextInt();
        
        if(!cdata.checkCustomerID(customer1) || !cdata.checkCustomerID(customer2)) {
            System.out.println("One or both customer IDs are invalid.");
            return;
        }
        
        LinkedList<Integer> customer1Products = getProductsReviewedByCustomer(customer1);
        LinkedList<Integer> customer2Products = getProductsReviewedByCustomer(customer2);
        
        System.out.println("Common highly-rated products (rating > 4):");
        boolean found = false;
        
        customer1Products.findFirst();
        while(!customer1Products.last()) {
            int productId = customer1Products.retrieve();
            
            customer2Products.findFirst();
            boolean common = false;
            while(!customer2Products.last()) {
                if(customer2Products.retrieve() == productId) {
                    common = true;
                    break;
                }
                customer2Products.findNext();
            }
            if (!customer2Products.last() && customer2Products.retrieve() == productId) {
                common = true;
            }
            
            if(common) {
                float avgRating = calculateAverageRating(productId);
                if(avgRating > 4.0) {
                    Product product = pdata.getProductData(productId);
                    if(product != null) {
                        System.out.println("- " + product.getName() + 
                                         " (ID: " + productId + 
                                         ", Avg Rating: " + String.format("%.2f", avgRating) + ")");
                        found = true;
                    }
                }
            }
            
            customer1Products.findNext();
        }
        if (!customer1Products.last()) {
            int productId = customer1Products.retrieve();
            
            customer2Products.findFirst();
            boolean common = false;
            while(!customer2Products.last()) {
                if(customer2Products.retrieve() == productId) {
                    common = true;
                    break;
                }
                customer2Products.findNext();
            }
            if (!customer2Products.last() && customer2Products.retrieve() == productId) {
                common = true;
            }
            
            if(common) {
                float avgRating = calculateAverageRating(productId);
                if(avgRating > 4.0) {
                    Product product = pdata.getProductData(productId);
                    if(product != null) {
                        System.out.println("- " + product.getName() + 
                                         " (ID: " + productId + 
                                         ", Avg Rating: " + String.format("%.2f", avgRating) + ")");
                        found = true;
                    }
                }
            }
        }
        
        if (!found) {
            System.out.println("No common highly-rated products found.");
        }
    }
    
    private static LinkedList<Integer> getProductsReviewedByCustomer(int customerId) {
        LinkedList<Integer> reviewedProducts = new LinkedList<>();
        
        reviews.findFirst();
        while(!reviews.last()) {
            if(reviews.retrieve().getCustomer() == customerId) {
                int productId = reviews.retrieve().getProduct();
                boolean exists = false;
                
                reviewedProducts.findFirst();
                while(!reviewedProducts.last()) {
                    if(reviewedProducts.retrieve() == productId) {
                        exists = true;
                        break;
                    }
                    reviewedProducts.findNext();
                }
                if (!reviewedProducts.last() && reviewedProducts.retrieve() == productId) {
                    exists = true;
                }
                
                if(!exists) {
                    reviewedProducts.insert(productId);
                }
            }
            reviews.findNext();
        }
        if (!reviews.last() && reviews.retrieve().getCustomer() == customerId) {
            int productId = reviews.retrieve().getProduct();
            boolean exists = false;
            
            reviewedProducts.findFirst();
            while(!reviewedProducts.last()) {
                if(reviewedProducts.retrieve() == productId) {
                    exists = true;
                    break;
                }
                reviewedProducts.findNext();
            }
            if (!reviewedProducts.last() && reviewedProducts.retrieve() == productId) {
                exists = true;
            }
            
            if(!exists) {
                reviewedProducts.insert(productId);
            }
        }
        
        return reviewedProducts;
    }
    
    private static float calculateAverageRating(int productId) {
        int totalRating = 0;
        int reviewCount = 0;
        
        reviews.findFirst();
        while(!reviews.last()) {
            if(reviews.retrieve().getProduct() == productId) {
                totalRating += reviews.retrieve().getRating();
                reviewCount++;
            }
            reviews.findNext();
        }
        if (!reviews.last() && reviews.retrieve().getProduct() == productId) {
            totalRating += reviews.retrieve().getRating();
            reviewCount++;
        }
        
        return reviewCount > 0 ? (float) totalRating / reviewCount : -1;
    }
    
    public static void main(String[] args) {
        System.out.println("=== E-COMMERCE INVENTORY & ORDER MANAGEMENT SYSTEM ===");
        System.out.println("Initializing system...");
        
        loadData();
        
        int choice;
        do {
            choice = mainMenu();
            switch(choice) {
                case 1:
                    productsMenu();
                    break;
                case 2:
                    customersMenu();
                    break;
                case 3:
                    ordersMenu();
                    break;
                case 4:
                    reviewsMenu();
                    break;
                case 5:
                    advancedQueriesMenu();
                    break;
                case 6:
                    System.out.println("Thank you for using the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while(choice != 6);
        
        input.close();
    }
}