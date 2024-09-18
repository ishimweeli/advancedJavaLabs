package com.designPatterns.adapter;

public class PaymentAdapterExercise {
    static class LegacyPaymentProcessor {
        void processPayment(double amount) {
            System.out.println("Legacy: Processing payment of $" + amount);
        }
    }

    interface ModernPaymentGateway {
        void charge(double amount);
    }

    static class PaymentAdapter implements ModernPaymentGateway {
        private LegacyPaymentProcessor legacyProcessor;

        PaymentAdapter(LegacyPaymentProcessor legacyProcessor) {
            this.legacyProcessor = legacyProcessor;
        }

        @Override
        public void charge(double amount) {
            legacyProcessor.processPayment(amount);
        }
    }

    public static void main(String[] args) {
        LegacyPaymentProcessor legacy = new LegacyPaymentProcessor();
        ModernPaymentGateway adapter = new PaymentAdapter(legacy);
        adapter.charge(100);
    }
}