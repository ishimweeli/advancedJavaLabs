package amalitech;

public class CustomerOrder {
    private String customerName;
    private double amount;

    public CustomerOrder(String customerName, double amount) {
        this.customerName = customerName;
        this.amount = amount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getAmount() {
        return amount;
    }
}
