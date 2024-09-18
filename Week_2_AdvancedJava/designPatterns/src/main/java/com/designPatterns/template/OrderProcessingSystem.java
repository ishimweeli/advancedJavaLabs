package com.designPatterns.template;

public class OrderProcessingSystem {
    abstract class OrderProcessor {
        public final void processOrder() {
            validateOrder();
            calculateTotalCost();
            applyDiscounts();
            shipOrder();
            sendConfirmation();
        }

        protected abstract void validateOrder();
        protected abstract void calculateTotalCost();
        protected void applyDiscounts() {
            // Default implementation
            System.out.println("Applying standard discounts");
        }
        protected abstract void shipOrder();
        protected void sendConfirmation() {
            // Default implementation
            System.out.println("Sending order confirmation email");
        }
    }

    class DomesticOrderProcessor extends OrderProcessor {
        protected void validateOrder() {
            System.out.println("Validating domestic order");
        }

        protected void calculateTotalCost() {
            System.out.println("Calculating total cost for domestic order");
        }

        protected void shipOrder() {
            System.out.println("Shipping domestic order");
        }
    }

    class InternationalOrderProcessor extends OrderProcessor {
        protected void validateOrder() {
            System.out.println("Validating international order");
        }

        protected void calculateTotalCost() {
            System.out.println("Calculating total cost for international order (including customs)");
        }

        protected void applyDiscounts() {
            System.out.println("Applying international order discounts");
        }

        protected void shipOrder() {
            System.out.println("Shipping international order");
        }

        protected void sendConfirmation() {
            System.out.println("Sending order confirmation email in customer's language");
        }
    }

    public static void main(String[] args) {
        OrderProcessingSystem system = new OrderProcessingSystem();
        OrderProcessor domesticProcessor = system.new DomesticOrderProcessor();
        OrderProcessor internationalProcessor = system.new InternationalOrderProcessor();

        System.out.println("Processing Domestic Order:");
        domesticProcessor.processOrder();

        System.out.println("\nProcessing International Order:");
        internationalProcessor.processOrder();
    }
}