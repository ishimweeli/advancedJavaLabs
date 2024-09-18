package amalitech;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderProcessor {
    public static void main(String[] args) {
        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem("Item1", 20.0));
        items.add(new OrderItem("Item2", 30.0));
        items.add(new OrderItem("Item3", 50.0));

        // Lambda and Streams for calculating total
        double total = items.stream()
                .mapToDouble(OrderItem::getPrice)
                .sum();
        System.out.println("Total: " + total);

        // Additional example for filtering and transforming
        double expensiveTotal = items.stream()
                .filter(item -> item.getPrice() > 25.0)
                .mapToDouble(OrderItem::getPrice)
                .sum();
        System.out.println("Total for expensive items: " + expensiveTotal);

        // Processing customer orders
        List<CustomerOrder> orders = new ArrayList<>();
        orders.add(new CustomerOrder("Customer1", 100.0));
        orders.add(new CustomerOrder("Customer2", 200.0));
        orders.add(new CustomerOrder("Customer1", 150.0));
        orders.add(new CustomerOrder("Customer3", 300.0));

        double totalAmount = orders.stream()
                .mapToDouble(CustomerOrder::getAmount)
                .sum();
        System.out.println("Total Order Amount: " + totalAmount);

        Map<String, Long> ordersPerCustomer = orders.stream()
                .collect(Collectors.groupingBy(CustomerOrder::getCustomerName, Collectors.counting()));
        System.out.println("Number of Orders per Customer: " + ordersPerCustomer);
    }
}
