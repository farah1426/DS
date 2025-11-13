package COMM;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class OrderManager {
    public static Scanner input = new Scanner(System.in);
    public static LinkedList<Order> orders = new LinkedList<Order>();
    
    public LinkedList<Order> getordersData() {
        return orders;
    }
    
    public OrderManager(String fileName) {
        loadOrdersFromFile(fileName);
    }
    
    private void loadOrdersFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            
            if (reader.hasNext()) {
                String header = reader.nextLine();
                System.out.println(" Reading orders header: " + header);
            }
            
            int loadedCount = 0;
            while(reader.hasNext()) {
                String line = reader.nextLine();
                String[] data = line.split(",");
                
                if (data.length < 6) {
                    System.out.println("️ Skipping invalid line: " + line);
                    continue;
                }
                
                try {
                    int orderId = Integer.parseInt(data[0].trim());
                    int customerId = Integer.parseInt(data[1].trim());
                    
                    String productsStr = data[2].replaceAll("\"", "").trim();
                    String[] productArray = productsStr.split(";");
                    Integer[] productIds = new Integer[productArray.length];
                    for (int i = 0; i < productArray.length; i++) {
                        productIds[i] = Integer.parseInt(productArray[i].trim());
                    }
                    
                    double totalPrice = Double.parseDouble(data[3].trim());
                    String dateStr = data[4].trim();
                    String status = data[5].trim();
                    
                    Order order = new Order(orderId, customerId, productIds, totalPrice, dateStr, status);
                    orders.insert(order);
                    loadedCount++;
                    
                } catch (Exception e) {
                    System.out.println("️ Error parsing order line: " + line);
                }
            }
            reader.close();
            System.out.println(" Successfully loaded " + loadedCount + " orders from CSV data");
            
        } catch (Exception ex) {
            System.out.println(" Error loading orders file: " + ex.getMessage());
        }
    }
    
    public int cancelOrder(int orderId) {
        orders.findFirst();//1
        while (!orders.last()) {//2
            if (orders.retrieve().getOrderId() == orderId) {//3
                Order order = orders.retrieve();//4
                if (order.getStatus().equalsIgnoreCase("cancelled")) {//5
                    System.out.println("Order was already cancelled");//6
                    return 2;//7
                }
                
                orders.remove();//8
                order.setStatus("Cancelled");//9
                orders.insert(order);//10
                return 1;//11
            }
            orders.findNext();//12
        }
        
        if (!orders.last() && orders.retrieve().getOrderId() == orderId) {//13
            Order order = orders.retrieve();//14
            if (order.getStatus().equalsIgnoreCase("cancelled")) {//15
                System.out.println("Order was already cancelled");//16
                return 2;//17
            }
            
            orders.remove();//18
            order.setStatus("Cancelled");//19
            orders.insert(order);//20
            return 1;//21
        }
        
        System.out.println("Order not found");//22
        return 0;//23
    }
    
    public boolean UpdateOrder(int orderID) {
        orders.findFirst();//1
        while (!orders.last()) {//2
            if (orders.retrieve().getOrderId() == orderID) {//3
                Order order = orders.retrieve();//4
                
                if (order.getStatus().equalsIgnoreCase("cancelled")) {//5
                    System.out.println("Cannot change status of cancelled order");//6
                    return false;//7
                }
                
                orders.remove();//8
                System.out.println("Current status: " + order.getStatus());//9
                System.out.print("Enter new status (Pending, Shipped, Delivered, Cancelled): ");//10
                String newStatus = input.next();//11
                order.setStatus(newStatus);//12
                orders.insert(order);//13
                return true;//14
            }
            orders.findNext();//15
        }
        
        if (!orders.last() && orders.retrieve().getOrderId() == orderID) {//16
            Order order = orders.retrieve();//17
            
            if (order.getStatus().equalsIgnoreCase("cancelled")) {//18
                System.out.println("Cannot change status of cancelled order");//19
                return false;//20
            }
            
            orders.remove();//21
            System.out.println("Current status: " + order.getStatus());//22
            System.out.print("Enter new status (Pending, Shipped, Delivered, Cancelled): ");//23
            String newStatus = input.next();//24
            order.setStatus(newStatus);//25
            orders.insert(order);//26
            return true;//27
        }
        
        System.out.println("Order not found");//28
        return false;//29
    }
    
    public Order searchOrderID(int orderID) {
        orders.findFirst();//1
        while (!orders.last()) {//2
            if (orders.retrieve().getOrderId() == orderID) {//3
                return orders.retrieve();//4
            }
            orders.findNext();//5
        }
        
        if (!orders.last() && orders.retrieve().getOrderId() == orderID) {//6
            return orders.retrieve();//7
        }
        
        System.out.println("Order not found");//8
        return null;//9
    }
    
    public LinkedList<Order> BetweenTwoDates(String date1, String date2) {
        LinkedList<Order> result = new LinkedList<>();//1
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");//2
            LocalDate startDate = LocalDate.parse(date1, formatter);//3
            LocalDate endDate = LocalDate.parse(date2, formatter);//4
            
            System.out.println(" Searching orders between " + startDate + " and " + endDate);//5
            
            int foundCount = 0;//6
            orders.findFirst();//7
            while (!orders.last()) {//8
                Order order = orders.retrieve();//9
                LocalDate orderDate = order.getDate();//10
                
                if ((!orderDate.isBefore(startDate) && !orderDate.isAfter(endDate))) {//11
                    result.insert(order);//12
                    System.out.println(order);//13
                    foundCount++;//14
                }
                orders.findNext();//15
            }
            
            if (!orders.last()) {//16
                Order order = orders.retrieve();//17
                LocalDate orderDate = order.getDate();//18
                
                if ((!orderDate.isBefore(startDate) && !orderDate.isAfter(endDate))) {//19
                    result.insert(order);//20
                    System.out.println(order);//21
                    foundCount++;//22
                }
            }
            
            System.out.println(" Found " + foundCount + " orders in date range");//24
            
        } catch (Exception ex) {//25
            System.out.println(" Error parsing dates. Please use format: yyyy-MM-dd");//26
        }
        
        return result;//27
    }
    
    public boolean checkOrderID(int orderId) {
        if (orders.empty()) return false;//1
        
        orders.findFirst();//2
        while (!orders.last()) {//3
            if (orders.retrieve().getOrderId() == orderId) {//4
                return true;//5
            }
            orders.findNext();//6
        }
        
        return !orders.last() && orders.retrieve().getOrderId() == orderId;//7
    }
}