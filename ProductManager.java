package COMM;

import java.io.File;
import java.util.Scanner;

public class ProductManager {
    public static Scanner input = new Scanner(System.in);
    public static LinkedList<Product> products = new LinkedList<Product>();
    
    public LinkedList<Product> getproductsData() {
        return products;
    }
    
    public ProductManager(String fileName) {
        loadProductsFromFile(fileName);
    }
    
    private void loadProductsFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            reader.nextLine(); // Skip header
            
            while(reader.hasNext()) {
                String line = reader.nextLine();
                String[] data = line.split(",");
                int productId = Integer.parseInt(data[0]);
                String name = data[1].trim();
                double price = Double.parseDouble(data[2]);
                int stock = Integer.parseInt(data[3]);
                
                Product product = new Product(productId, name, price, stock);
                products.insert(product);
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println("Error loading products: " + ex.getMessage());
        }
    }
    
    public void addProduct() {
        System.out.print("Enter product ID: ");
        int productId = input.nextInt();
        while (checkProductID(productId)) {
            System.out.print("Product ID already exists, enter different ID: ");
            productId = input.nextInt();
        }
        
        System.out.print("Enter product name: ");
        String name = input.next();
        
        System.out.print("Enter product price: ");
        double price = input.nextDouble();
        
        System.out.print("Enter product stock: ");
        int stock = input.nextInt();
        
        Product newProduct = new Product(productId, name, price, stock);
        products.insert(newProduct);
        System.out.println("Product added successfully");
    }
    
    public Product removeProduct() {
        if (products.empty()) {
            System.out.println("No products available");
            return null;
        }
        
        System.out.print("Enter product ID to remove: ");
        int productId = input.nextInt();
        
        products.findFirst();
        while (!products.last()) {
            if (products.retrieve().getProductId() == productId) {
                Product removed = products.retrieve();
                products.remove();
                return removed;
            }
            products.findNext();
        }
        
        if (!products.last() && products.retrieve().getProductId() == productId) {
            Product removed = products.retrieve();
            products.remove();
            return removed;
        }
        
        System.out.println("Product not found");
        return null;
    }
    
    public void updateProduct() {
        if (products.empty()) {
            System.out.println("No products available");
            return;
        }
        
        System.out.print("Enter product ID to update: ");
        int productId = input.nextInt();
        
        products.findFirst();
        while (!products.last()) {
            if (products.retrieve().getProductId() == productId) {
                Product product = products.retrieve();
                products.remove();
                
                System.out.println("1. Update Name");
                System.out.println("2. Update Price");
                System.out.println("3. Update Stock");
                System.out.print("Choose field to update: ");
                int choice = input.nextInt();
                
                switch(choice) {
                    case 1:
                        System.out.print("Enter new name: ");
                        product.setName(input.next());
                        break;
                    case 2:
                        System.out.print("Enter new price: ");
                        product.setPrice(input.nextDouble());
                        break;
                    case 3:
                        System.out.print("Enter new stock: ");
                        product.setStock(input.nextInt());
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
                
                products.insert(product);
                System.out.println("Product updated successfully");
                return;
            }
            products.findNext();
        }
        
        if (!products.last() && products.retrieve().getProductId() == productId) {
            Product product = products.retrieve();
            products.remove();
            
            System.out.println("1. Update Name");
            System.out.println("2. Update Price");
            System.out.println("3. Update Stock");
            System.out.print("Choose field to update: ");
            int choice = input.nextInt();
            
            switch(choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    product.setName(input.next());
                    break;
                case 2:
                    System.out.print("Enter new price: ");
                    product.setPrice(input.nextDouble());
                    break;
                case 3:
                    System.out.print("Enter new stock: ");
                    product.setStock(input.nextInt());
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            
            products.insert(product);
            System.out.println("Product updated successfully");
            return;
        }
        
        System.out.println("Product not found");
    }
    
    public Product searchProducID() {
        System.out.print("Enter product ID: ");
        int productId = input.nextInt();
        
        products.findFirst();
        while (!products.last()) {
            if (products.retrieve().getProductId() == productId) {
                return products.retrieve();
            }
            products.findNext();
        }
        
        if (!products.last() && products.retrieve().getProductId() == productId) {
            return products.retrieve();
        }
        
        System.out.println("Product not found");
        return null;
    }
    
    public Product searchProducName() {
        System.out.print("Enter product name: ");
        String name = input.nextLine();
        
        products.findFirst();
        while (!products.last()) {
            if (products.retrieve().getName().equalsIgnoreCase(name)) {
                return products.retrieve();
            }
            products.findNext();
        }
        
        if (!products.last() && products.retrieve().getName().equalsIgnoreCase(name)) {
            return products.retrieve();
        }
        
        System.out.println("Product not found");
        return null;
    }
    
    public void Out_Stock_Products() {
        if (products.empty()) {//1
            System.out.println("No products available");//2
            return;//3
        }
        
        System.out.println("Out of stock products:");//4
        products.findFirst();//5
        while (!products.last()) {//6
            if (products.retrieve().getStock() == 0) {//7
                System.out.println(products.retrieve());//8
            }
            products.findNext();//9
        }
        
        if (!products.last() && products.retrieve().getStock() == 0) {//10
            System.out.println(products.retrieve());//11
        }
        
    }
    
    public boolean checkProductID(int productId) {
        if (products.empty()) return false;
        
        products.findFirst();
        while (!products.last()) {
            if (products.retrieve().getProductId() == productId) {
                return true;
            }
            products.findNext();
        }
        
        return !products.last() && products.retrieve().getProductId() == productId;
    }
    
    public boolean AvailableProductID(int productId) {
        if (products.empty()) return false;
        
        products.findFirst();
        while (!products.last()) {
            Product product = products.retrieve();
            if (product.getProductId() == productId && product.getStock() > 0) {
                return true;
            }
            products.findNext();
        }
        
        if (!products.last()) {
            Product product = products.retrieve();
            return product.getProductId() == productId && product.getStock() > 0;
        }
        
        return false;
    }
    
    public Product getProductData(int productId) {
        if (products.empty()) return null;
        
        products.findFirst();
        while (!products.last()) {
            if (products.retrieve().getProductId() == productId) {
                return products.retrieve();
            }
            products.findNext();
        }
        
        if (!products.last() && products.retrieve().getProductId() == productId) {
            return products.retrieve();
        }
        
        return null;
    }
}