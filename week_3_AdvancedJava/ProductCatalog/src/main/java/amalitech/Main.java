package amalitech;

import amalitech.Product;

public class Main {
    public static void main(String[] args) {
        Product product = new ElectronicsProduct("Laptop", 999.99);
        System.out.println("Name: " + product.getName());
        System.out.println("Price: " + product.getPrice());

        String categoryLabel = switch (product) {
            case ElectronicsProduct e -> "Electronic Devices";
            case ClothingProduct c -> "Apparel and Accessories";
            default -> "Other";
        };

        System.out.println("Category Label: " + categoryLabel);
    }
}
