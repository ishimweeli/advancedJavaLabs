package com.designPatterns.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockMonitor {
    private Map<String, List<Observer>> observers = new HashMap<>();
    private Map<String, Double> stocks = new HashMap<>();

    public void addStock(String symbol, double price) {
        stocks.put(symbol, price);
        observers.put(symbol, new ArrayList<>());
    }

    public void registerObserver(String symbol, Observer o) {
        observers.get(symbol).add(o);
    }

    public void removeObserver(String symbol, Observer o) {
        observers.get(symbol).remove(o);
    }

    public void notifyObservers(String symbol) {
        for (Observer observer : observers.get(symbol)) {
            observer.update(symbol, stocks.get(symbol));
        }
    }

    public void setStockPrice(String symbol, double price) {
        if (stocks.containsKey(symbol) && stocks.get(symbol) != price) {
            stocks.put(symbol, price);
            notifyObservers(symbol);
        }
    }

    interface Observer {
        void update(String symbol, double price);
    }

    static class StockDisplay implements Observer {
        public void update(String symbol, double price) {
            System.out.println("Stock Display: " + symbol + " price updated to $" + price);
        }
    }

    public static void main(String[] args) {
        StockMonitor monitor = new StockMonitor();
        monitor.addStock("AAPL", 150.0);
        monitor.addStock("GOOGL", 2800.0);

        StockDisplay display1 = new StockDisplay();
        StockDisplay display2 = new StockDisplay();

        monitor.registerObserver("AAPL", display1);
        monitor.registerObserver("GOOGL", display2);

        monitor.setStockPrice("AAPL", 155.0);
        monitor.setStockPrice("GOOGL", 2850.0);
    }
}
