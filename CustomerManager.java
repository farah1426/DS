package COMM;

import java.io.File;
import java.util.Scanner;

public class CustomerManager {
    public static Scanner input = new Scanner(System.in);
    public static LinkedList<Customer> customers = new LinkedList<Customer>();
    
    public LinkedList<Customer> getcustomersData() {
        return customers;
    }
    
    public CustomerManager(String fileName) {
        loadCustomersFromFile(fileName);
    }
    
    private void loadCustomersFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            reader.nextLine(); // Skip header
            
            while(reader.hasNext()) {
                String line = reader.nextLine();
                String[] data = line.split(",");
                int customerId = Integer.parseInt(data[0]);
                String name = data[1].trim();
                String email = data[2].trim();
                
                Customer customer = new Customer(customerId, name, email);
                customers.insert(customer);
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println("Error loading customers: " + ex.getMessage());
        }
    }
    
    public void RegisterCustomer() {
        Customer customer = new Customer();
        
        System.out.print("Enter customer ID: ");
        customer.setCustomerId(input.nextInt());
        
        while (checkCustomerID(customer.getCustomerId())) {
            System.out.print("Customer ID already exists, enter different ID: ");
            customer.setCustomerId(input.nextInt());
        }
        
        System.out.print("Enter customer name: ");
        customer.setName(input.next());
        
        System.out.print("Enter customer email: ");
        customer.setEmail(input.next());
        
        customers.insert(customer);
        System.out.println("Customer registered successfully");
    }
    
    public void OrderHistory() {
        if (customers.empty()) {
            System.out.println("No customers available");
            return;
        }
        
        System.out.print("Enter customer ID: ");
        int customerId = input.nextInt();
        
        customers.findFirst();
        while (!customers.last()) {
            if (customers.retrieve().getCustomerId() == customerId) {
                Customer customer = customers.retrieve();
                LinkedList<Integer> orders = customer.getOrders();
                
                if (orders.empty()) {
                    System.out.println("No order history for customer " + customerId);
                } else {
                    System.out.println("Order history for customer " + customerId + ":");
                    orders.findFirst();
                    while (!orders.last()) {
                        System.out.println("Order ID: " + orders.retrieve());
                        orders.findNext();
                    }
                    if (!orders.last()) {
                        System.out.println("Order ID: " + orders.retrieve());
                    }
                }
                return;
            }
            customers.findNext();
        }
        
        if (!customers.last() && customers.retrieve().getCustomerId() == customerId) {
            Customer customer = customers.retrieve();
            LinkedList<Integer> orders = customer.getOrders();
            
            if (orders.empty()) {
                System.out.println("No order history for customer " + customerId);
            } else {
                System.out.println("Order history for customer " + customerId + ":");
                orders.findFirst();
                while (!orders.last()) {
                    System.out.println("Order ID: " + orders.retrieve());
                    orders.findNext();
                }
                if (!orders.last()) {
                    System.out.println("Order ID: " + orders.retrieve());
                }
            }
            return;
        }
        
        System.out.println("Customer not found");
    }
    
    public boolean checkCustomerID(int customerId) {
        if (customers.empty()) return false;
        
        customers.findFirst();
        while (!customers.last()) {
            if (customers.retrieve().getCustomerId() == customerId) {
                return true;
            }
            customers.findNext();
        }
        
        return !customers.last() && customers.retrieve().getCustomerId() == customerId;
    }
    
    public Customer getCustomerID() {
        if (customers.empty()) {
            System.out.println("No customers available");
            return null;
        }
        
        System.out.print("Enter customer ID: ");
        int customerId = input.nextInt();
        
        customers.findFirst();
        while (!customers.last()) {
            if (customers.retrieve().getCustomerId() == customerId) {
                return customers.retrieve();
            }
            customers.findNext();
        }
        
        if (!customers.last() && customers.retrieve().getCustomerId() == customerId) {
            return customers.retrieve();
        }
        
        System.out.println("Customer not found");
        return null;
    }
}