// 1. Adapter Pattern - Exercise 1
class PaymentAdapterExercise {
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

    static void run() {
        LegacyPaymentProcessor legacy = new LegacyPaymentProcessor();
        ModernPaymentGateway adapter = new PaymentAdapter(legacy);
        adapter.charge(100);
    }
}

// 2. Adapter Pattern - Exercise 2
class DataSourceAdapterExercise {
    static class CSVDataSource {
        String readCSV() {
            return "data from CSV";
        }
    }

    interface CommonDataInterface {
        String getData();
    }

    static class CSVAdapter implements CommonDataInterface {
        private CSVDataSource csvSource;

        CSVAdapter(CSVDataSource csvSource) {
            this.csvSource = csvSource;
        }

        @Override
        public String getData() {
            return csvSource.readCSV();
        }
    }

    static void run() {
        CSVDataSource csvSource = new CSVDataSource();
        CommonDataInterface adapter = new CSVAdapter(csvSource);
        System.out.println(adapter.getData());
    }
}

// 3. Decorator Pattern - Exercise 1
class ShapeDecoratorExercise {
    interface Shape {
        String draw();
    }

    static class Circle implements Shape {
        @Override
        public String draw() {
            return "Drawing Circle";
        }
    }

    static abstract class ShapeDecorator implements Shape {
        protected Shape shape;

        ShapeDecorator(Shape shape) {
            this.shape = shape;
        }

        @Override
        public String draw() {
            return shape.draw();
        }
    }

    static class ColoredBorderDecorator extends ShapeDecorator {
        ColoredBorderDecorator(Shape shape) {
            super(shape);
        }

        @Override
        public String draw() {
            return super.draw() + " with colored border";
        }
    }

    static void run() {
        Shape circle = new Circle();
        Shape decoratedCircle = new ColoredBorderDecorator(circle);
        System.out.println(decoratedCircle.draw());
    }
}

// 4. Decorator Pattern - Exercise 2
class MessageDecoratorExercise {
    interface Message {
        String send(String text);
    }

    static class SimpleMessage implements Message {
        @Override
        public String send(String text) {
            return text;
        }
    }

    static class EncryptionDecorator implements Message {
        private Message message;

        EncryptionDecorator(Message message) {
            this.message = message;
        }

        @Override
        public String send(String text) {
            String encrypted = encrypt(text);
            return message.send(encrypted);
        }

        private String encrypt(String text) {
            return "ENCRYPTED(" + text + ")";
        }
    }

    static void run() {
        Message baseMessage = new SimpleMessage();
        Message encryptedMessage = new EncryptionDecorator(baseMessage);
        System.out.println(encryptedMessage.send("Hello, World!"));
    }
}

// 5. Facade Pattern - Exercise 1
class ShoppingCartFacadeExercise {
    static class Cart {
        void addItem(String item) {
            System.out.println("Added " + item + " to cart");
        }
    }

    static class DiscountCalculator {
        void calculate(Cart cart) {
            System.out.println("Calculating discount");
        }
    }

    static class Checkout {
        void process(Cart cart) {
            System.out.println("Processing checkout");
        }
    }

    static class ShoppingCartFacade {
        private Cart cart;
        private DiscountCalculator discount;
        private Checkout checkout;

        ShoppingCartFacade() {
            this.cart = new Cart();
            this.discount = new DiscountCalculator();
            this.checkout = new Checkout();
        }

        void shop(String item) {
            cart.addItem(item);
            discount.calculate(cart);
            checkout.process(cart);
        }
    }

    static void run() {
        ShoppingCartFacade facade = new ShoppingCartFacade();
        facade.shop("Laptop");
    }
}

// 6. Facade Pattern - Exercise 2
class EmailFacadeExercise {
    static class EmailComposer {
        void compose(String content) {
            System.out.println("Composing email: " + content);
        }
    }

    static class EmailSender {
        void send() {
            System.out.println("Sending email");
        }
    }

    static class EmailManager {
        void archive() {
            System.out.println("Archiving email");
        }
    }

    static class EmailFacade {
        private EmailComposer composer;
        private EmailSender sender;
        private EmailManager manager;

        EmailFacade() {
            this.composer = new EmailComposer();
            this.sender = new EmailSender();
            this.manager = new EmailManager();
        }

        void sendEmail(String content) {
            composer.compose(content);
            sender.send();
            manager.archive();
        }
    }

    static void run() {
        EmailFacade facade = new EmailFacade();
        facade.sendEmail("Hello, this is a test email.");
    }
}

// 7. Proxy Pattern - Exercise 1
class RemoteServiceProxyExercise {
    interface RemoteService {
        String fetchData();
    }

    static class RealRemoteService implements RemoteService {
        @Override
        public String fetchData() {
            return "Data from remote service";
        }
    }

    static class RemoteServiceProxy implements RemoteService {
        private RemoteService service;
        private String cache;

        RemoteServiceProxy() {
            this.service = new RealRemoteService();
        }

        @Override
        public String fetchData() {
            if (cache == null) {
                System.out.println("Fetching data from remote service");
                cache = service.fetchData();
            } else {
                System.out.println("Returning cached data");
            }
            return cache;
        }
    }

    static void run() {
        RemoteService proxy = new RemoteServiceProxy();
        System.out.println(proxy.fetchData());
        System.out.println(proxy.fetchData());
    }
}

// 8. Proxy Pattern - Exercise 2
class FileDownloadProxyExercise {
    interface FileDownloader {
        void download(String file);
    }

    static class RealFileDownloader implements FileDownloader {
        @Override
        public void download(String file) {
            System.out.println("Downloading " + file);
        }
    }

    static class FileDownloadProxy implements FileDownloader {
        private FileDownloader downloader;

        FileDownloadProxy() {
            this.downloader = new RealFileDownloader();
        }

        @Override
        public void download(String file) {
            if (checkAuthorization()) {
                System.out.println("Authorization successful");
                reportProgress("Starting download");
                downloader.download(file);
                reportProgress("Download complete");
            } else {
                System.out.println("Authorization failed");
            }
        }

        private boolean checkAuthorization() {
            return true; // Simplified for this example
        }

        private void reportProgress(String status) {
            System.out.println("Progress: " + status);
        }
    }

    static void run() {
        FileDownloader proxy = new FileDownloadProxy();
        proxy.download("example.zip");
    }
}

public class Main {
    public static void main(String[] args) {
        PaymentAdapterExercise.run();
        DataSourceAdapterExercise.run();
        ShapeDecoratorExercise.run();
        MessageDecoratorExercise.run();
        ShoppingCartFacadeExercise.run();
        EmailFacadeExercise.run();
        RemoteServiceProxyExercise.run();
        FileDownloadProxyExercise.run();
    }
}