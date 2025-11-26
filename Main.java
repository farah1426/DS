/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ddss;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    
    public static Scanner input = new Scanner(System.in);
    
    // Phase 2 - Using AVL Trees
    public static ProductManager pdata = new ProductManager("products.csv");
    public static AVLTree<Integer, Product> products;
    
    public static CustomerManager cdata = new CustomerManager("customers.csv");
    public static AVLTree<Integer, Customer> customers;
    
    public static OrderManager odata = new OrderManager("orders.csv");
    public static AVLTree<Integer, Order> orders;
    
    public static ReviewManager rdata = new ReviewManager("reviews.csv");
    public static AVLTree<Integer, Review> reviews;
    
    public static void loadData() {
        
        System.out.println("LOADING CSV DATA USING AVL TREES");
        
        
        products = pdata.getproductsData();
        customers = cdata.getcustomersData();
        orders = odata.getordersData();
        reviews = rdata.getreviewsData();
        
        System.out.println("\n");
       
        System.out.println("----------------------------------------------");
        System.out.println("Products: " + products.size() + " items");
        System.out.println("Customers: " + customers.size() + " customers");
        System.out.println("Orders: " + orders.size() + " orders");
        System.out.println("Reviews: " + reviews.size() + " reviews");
        
        linkOrdersToCustomers();
        linkReviewsToProducts();
        
        System.out.println("\n----------------------------------------------");
        System.out.println("ALL DATA LOADED SUCCESSFULLY!");
        System.out.println("Using AVL Trees - O(log n) operations");
        System.out.println("------------------------------------------------\n");
    }
    
    private static void linkOrdersToCustomers() {
        LinkedList<Customer> allCustomers = customers.getAllData();
        LinkedList<Order> allOrders = orders.getAllData();
        
        if (allCustomers.empty() || allOrders.empty()) return;
        
        allCustomers.findFirst();
        while (!allCustomers.last()) {
            Customer customer = allCustomers.retrieve();
            allOrders.findFirst();
            while (!allOrders.last()) {
                Order order = allOrders.retrieve();
                if (customer.getCustomerId() == order.getCustomerRefrence()) {
                    customer.addOrder(order.getOrderId());
                }
                allOrders.findNext();
            }
            if (!allOrders.last()) {
                Order order = allOrders.retrieve();
                if (customer.getCustomerId() == order.getCustomerRefrence()) {
                    customer.addOrder(order.getOrderId());
                }
            }
            allCustomers.findNext();
        }
        if (!allCustomers.last()) {
            Customer customer = allCustomers.retrieve();
            allOrders.findFirst();
            while (!allOrders.last()) {
                Order order = allOrders.retrieve();
                if (customer.getCustomerId() == order.getCustomerRefrence()) {
                    customer.addOrder(order.getOrderId());
                }
                allOrders.findNext();
            }
            if (!allOrders.last()) {
                Order order = allOrders.retrieve();
                if (customer.getCustomerId() == order.getCustomerRefrence()) {
                    customer.addOrder(order.getOrderId());
                }
            }
        }
    }
    
    private static void linkReviewsToProducts() {
        LinkedList<Product> allProducts = products.getAllData();
        LinkedList<Review> allReviews = reviews.getAllData();
        
        if (allProducts.empty() || allReviews.empty()) return;
        
        allProducts.findFirst();
        while (!allProducts.last()) {
            Product product = allProducts.retrieve();
            allReviews.findFirst();
            while (!allReviews.last()) {
                Review review = allReviews.retrieve();
                if (product.getProductId() == review.getProduct()) {
                    product.addReview(review.getReviewId());
                }
                allReviews.findNext();
            }
            if (!allReviews.last()) {
                Review review = allReviews.retrieve();
                if (product.getProductId() == review.getProduct()) {
                    product.addReview(review.getReviewId());
                }
            }
            allProducts.findNext();
        }
        if (!allProducts.last()) {
            Product product = allProducts.retrieve();
            allReviews.findFirst();
            while (!allReviews.last()) {
                Review review = allReviews.retrieve();
                if (product.getProductId() == review.getProduct()) {
                    product.addReview(review.getReviewId());
                }
                allReviews.findNext();
            }
            if (!allReviews.last()) {
                Review review = allReviews.retrieve();
                if (product.getProductId() == review.getProduct()) {
                    product.addReview(review.getReviewId());
                }
            }
        }
    }
    
    public static int mainMenu() {
        System.out.println("\n");
        System.out.println("E-COMMERCE SYSTEM - PHASE 2 (AVL TREES)");
        
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
            System.out.println("\n--- PRODUCTS MANAGEMENT (AVL Tree - O(log n)) ---");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Update Product");
            System.out.println("4. Search Product by ID");
            System.out.println("5. Search Product by Name");
            System.out.println("6. Search Products by Price Range");
            System.out.println("7. View Out-of-Stock Products");
            System.out.println("8. Back to Main Menu");
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
                    Product foundById = pdata.searchProductByID();
                    if(foundById != null) {
                        System.out.println("Product found: " + foundById);
                    }
                    break;
                case 5:
                    Product foundByName = pdata.searchProductByName();
                    if(foundByName != null) {
                        System.out.println("Product found: " + foundByName);
                    }
                    break;
                case 6:
                    pdata.searchProductsByPriceRange();
                    break;
                case 7:
                    pdata.outOfStockProducts();
                    break;
                case 8:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while(choice != 8);
    }
    
    public static void customersMenu() {
        int choice;
        do {
            System.out.println("\n--- CUSTOMERS MANAGEMENT (AVL Tree - O(log n)) ---");
            System.out.println("1. Register New Customer");
            System.out.println("2. Place New Order");
            System.out.println("3. View Order History");
            System.out.println("4. View Customer Reviews");
            System.out.println("5. List All Customers (Sorted Alphabetically)");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            
            switch(choice) {
                case 1:
                    cdata.registerCustomer();
                    break;
                case 2:
                    placeOrderForCustomer(); 
                    break;
                case 3:
                    cdata.viewOrderHistory();
                    break;
                case 4:
                    extractCustomerReviews();
                    break;
                case 5:
                    cdata.listCustomersSorted();
                    break;
                case 6:
                    System.out.println("Returning to main menu");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while(choice != 6);
    }
    
    public static void ordersMenu() {
        int choice;
        do {
            System.out.println("\n--- ORDERS MANAGEMENT (AVL Tree - O(log n)) ---");
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
                    placeOrder(); 
                    break;
                case 2:
                    cancelOrder();
                    break;
                case 3:
                    System.out.print("Enter order ID to update: ");
                    int orderId = input.nextInt();
                    odata.updateOrderStatus(orderId);
                    break;
                case 4:
                    Order foundOrder = odata.searchOrderByID();
                    if(foundOrder != null) {
                        System.out.println("Order found: " + foundOrder);
                    }
                    break;
                case 5:
                    viewOrdersBetweenDates();
                    break;
                case 6:
                    System.out.println("Returning to main menu");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while(choice != 6);
    }
    
    public static void reviewsMenu() {
        int choice;
        do {
            System.out.println("\n--- REVIEWS MANAGEMENT (AVL Tree - O(log n)) ---");
            System.out.println("1. Add Review");
            System.out.println("2. Edit Review");
            System.out.println("3. Get Average Rating for Product");
            System.out.println("4. Display Customers Who Reviewed Product");
            System.out.println("5. Back to Main Menu");
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
                    System.out.print("Enter product ID: ");
                    int productId = input.nextInt();
                    rdata.displayCustomersWhoReviewedProduct(productId);
                    break;
                case 5:
                    System.out.println("Returning to main menu");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while(choice != 5);
    }
    
    public static void advancedQueriesMenu() {
        int choice;
        do {
            System.out.println("\n");
            System.out.println("ADVANCED QUERIES:");
            
            System.out.println("1. Find All Orders Between Two Dates");
            System.out.println("2. List All Products Within a Price Range");
            System.out.println("3. Top 3 Most Reviewed/Highest Rated Products");
            System.out.println("4. List All Customers Sorted Alphabetically");
            System.out.println("5. Display Customers Who Reviewed a Product");
            System.out.println("6. Common Highly-Rated Products Between Customers");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            
            switch(choice) {
                case 1:
                    viewOrdersBetweenDates();
                    break;
                case 2:
                    pdata.searchProductsByPriceRange();
                    break;
                case 3:
                    showTop3Products();
                    break;
                case 4:
                    cdata.listCustomersSorted();
                    break;
                case 5:
                    System.out.print("Enter product ID: ");
                    int prodId = input.nextInt();
                    rdata.displayCustomersWhoReviewedProduct(prodId);
                    break;
                case 6:
                    showCommonHighlyRatedProducts();
                    break;
                case 7:
                    System.out.println("Returning to main menu");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while(choice != 7);
    }
    
    // Advanced Query 1: Find All Orders Between Two Dates
    public static void viewOrdersBetweenDates() {
        System.out.print("Enter start date (yyyy-MM-dd): "); 
        String startDate = input.next();
        
        System.out.print("Enter end date (yyyy-MM-dd): "); 
        String endDate = input.next();
        
        odata.findOrdersBetweenDates(startDate, endDate);
    }
    
    // Advanced Query 3: Top 3 Products by Rating
    public static void showTop3Products() {
        LinkedPQ<Product> topProducts = new LinkedPQ<>();
        LinkedList<Product> allProducts = products.getAllData();
        
        if (allProducts.empty()) {
            System.out.println("No products available");
            return;
        }
        
        allProducts.findFirst();
        while(!allProducts.last()) {
            Product product = allProducts.retrieve();
            float avgRating = calculateAverageRating(product.getProductId());
            if(avgRating > 0) {
                topProducts.enqueue(product, avgRating);
            }
            allProducts.findNext();
        }
        if (!allProducts.last()) {
            Product product = allProducts.retrieve();
            float avgRating = calculateAverageRating(product.getProductId());
            if(avgRating > 0) {
                topProducts.enqueue(product, avgRating);
            }
        }
        
        System.out.println("\n");
        System.out.println("TOP 3 PRODUCTS BY AVERAGE RATING");
 
        
        for(int i = 1; i <= 3 && !topProducts.empty(); i++) {
            PQElement<Product> topProduct = topProducts.serve();
            System.out.println(i + ". " + topProduct.data.getName() + 
                             " - Rating: " + (topProduct.priority) +
                             " - Price: $" + topProduct.data.getPrice() +
                             " - Reviews: " + topProduct.data.getReviews().size());
        }
    }
    
    // Advanced Query 6: Common Highly-Rated Products Between Customers
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
        
        System.out.println("\n");
        System.out.println("COMMON HIGHLY-RATED PRODUCTS (Rating > 4)");
    
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
                        System.out.println("Name: " + product.getName() + 
                                         " (ID: " + productId + 
                                         ", Avg Rating: " +(avgRating) + ")");
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
                        System.out.println("Name: " + product.getName() + 
                                         " (ID: " + productId + 
                                         ", Avg Rating: " + (avgRating) + ")");
                        found = true;
                    }
                }
            }
        }
        
        if (!found) {
            System.out.println("No common highly-rated products found.");
        }
    }
    
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
                products.update(productId, product);
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
        
        orders.insert(orderId, newOrder);
        
        Customer customer = customers.search(customerId);
        if (customer != null) {
            customer.addOrder(orderId);
            customers.update(customerId, customer);
        }
        
        System.out.println("Order placed successfully in O(log n) time! Total: $" + totalPrice);
    }
    
    public static void placeOrder() {
        placeOrderForCustomer();
    }
    
    public static void cancelOrder() {
        System.out.print("Enter order ID to cancel: ");
        int orderId = input.nextInt();
        
        int result = odata.cancelOrder(orderId);
        if(result == 1) {
            Order cancelledOrder = odata.searchOrderByID(orderId);
            if(cancelledOrder != null) {
                LinkedList<Integer> orderProducts = cancelledOrder.getProducts();
                orderProducts.findFirst();
                while(!orderProducts.last()) {
                    int productId = orderProducts.retrieve();
                    Product product = pdata.getProductData(productId);
                    if(product != null) {
                        product.setStock(product.getStock() + 1);
                        products.update(productId, product);
                    }
                    orderProducts.findNext();
                }
                if (!orderProducts.last()) {
                    int productId = orderProducts.retrieve();
                    Product product = pdata.getProductData(productId);
                    if(product != null) {
                        product.setStock(product.getStock() + 1);
                        products.update(productId, product);
                    }
                }
            }
        }
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
        
        Review newReview = rdata.addReview(customerId, productId);
        if (newReview != null) {
            Product product = products.search(productId);
            if (product != null) {
                product.addReview(newReview.getReviewId());
                products.update(productId, product);
            }
        }
    }
    
    public static void getAverageRating() {
        System.out.print("Enter product ID: ");
        int productId = input.nextInt();
        
        float avgRating = calculateAverageRating(productId);
        if(avgRating >= 0) {
            System.out.println("Average rating for product " + productId + ": " + String.format("%.2f", avgRating));
        } else {
            System.out.println("No reviews found for this product.");
        }
    }
    
    public static void extractCustomerReviews() {
        System.out.print("Enter customer ID: ");
        int customerId = input.nextInt();
        
        LinkedList<Review> allReviews = reviews.getAllData();
        LinkedList<Review> customerReviews = new LinkedList<>();
        
        allReviews.findFirst();
        while(!allReviews.last()) {
            if(allReviews.retrieve().getCustomer() == customerId) {
                customerReviews.insert(allReviews.retrieve());
            }
            allReviews.findNext();
        }
        if (!allReviews.last() && allReviews.retrieve().getCustomer() == customerId) {
            customerReviews.insert(allReviews.retrieve());
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
    
    private static LinkedList<Integer> getProductsReviewedByCustomer(int customerId) {
        LinkedList<Integer> reviewedProducts = new LinkedList<>();
        LinkedList<Review> allReviews = reviews.getAllData();
        
        allReviews.findFirst();
        while(!allReviews.last()) {
            if(allReviews.retrieve().getCustomer() == customerId) {
                int productId = allReviews.retrieve().getProduct();
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
            allReviews.findNext();
        }
        if (!allReviews.last() && allReviews.retrieve().getCustomer() == customerId) {
            int productId = allReviews.retrieve().getProduct();
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
        
        LinkedList<Review> allReviews = reviews.getAllData();
        
        allReviews.findFirst();
        while(!allReviews.last()) {
            if(allReviews.retrieve().getProduct() == productId) {
                totalRating += allReviews.retrieve().getRating();
                reviewCount++;
            }
            allReviews.findNext();
        }
        if (!allReviews.last() && allReviews.retrieve().getProduct() == productId) {
            totalRating += allReviews.retrieve().getRating();
            reviewCount++;
        }
        
        return reviewCount > 0 ? (float) totalRating / reviewCount : -1;
    }
    

    
    public static void main(String[] args) {
  
        System.out.println("E-COMMERCE INVENTORY");
        System.out.println("PHASE 2 - AVL TREES");
      
        System.out.println("\nInitializing system with AVL Trees\n");
        
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
                    System.out.println("\n");
                    System.out.println("Thank you for using the system. Goodbye!");
                
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while(choice != 6);
        
        input.close();
    }
}
