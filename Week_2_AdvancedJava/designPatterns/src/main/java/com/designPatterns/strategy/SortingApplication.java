package com.designPatterns.strategy;

import java.util.Arrays;

public class SortingApplication {
    private SortStrategy strategy;

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void sort(int[] array) {
        strategy.sort(array);
    }

    interface SortStrategy {
        void sort(int[] array);
    }

    static class BubbleSort implements SortStrategy {
        public void sort(int[] array) {
            System.out.println("Sorting using Bubble Sort");
            // Bubble sort implementation
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
        }
    }

    static class SelectionSort implements SortStrategy {
        public void sort(int[] array) {
            System.out.println("Sorting using Selection Sort");
            // Selection sort implementation
            for (int i = 0; i < array.length - 1; i++) {
                int minIdx = i;
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j] < array[minIdx]) {
                        minIdx = j;
                    }
                }
                int temp = array[minIdx];
                array[minIdx] = array[i];
                array[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        SortingApplication app = new SortingApplication();
        int[] array = {64, 34, 25, 12, 22, 11, 90};

        app.setStrategy(new BubbleSort());
        app.sort(array);
        System.out.println(Arrays.toString(array));

        array = new int[]{64, 34, 25, 12, 22, 11, 90};
        app.setStrategy(new SelectionSort());
        app.sort(array);
        System.out.println(Arrays.toString(array));
    }
}