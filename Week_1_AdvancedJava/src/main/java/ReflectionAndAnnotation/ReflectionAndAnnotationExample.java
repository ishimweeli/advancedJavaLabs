package ReflectionAndAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String name();
}

class OrderProcessor {
    @MyAnnotation(name = "processOrder")
    public void processOrder(String orderId) {
        System.out.println("Processing order: " + orderId);
    }
}

public class ReflectionAndAnnotationExample {
    public static void main(String[] args) {
        try {
            Class<?> orderProcessorClass = OrderProcessor.class;
            Method processOrderMethod = orderProcessorClass.getMethod("processOrder", String.class);

            if (processOrderMethod.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation annotation = processOrderMethod.getAnnotation(MyAnnotation.class);
                System.out.println("Method name (from annotation): " + annotation.name());
            }

            OrderProcessor processor = new OrderProcessor();
            processOrderMethod.invoke(processor, "12345");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
