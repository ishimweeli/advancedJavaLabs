package com.designPatterns.decorator;

public class ShapeDecoratorExercise {
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

    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape decoratedCircle = new ColoredBorderDecorator(circle);
        System.out.println(decoratedCircle.draw());
    }
}