package com.designPatterns.observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherStation {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;
    private float humidity;
    private float pressure;

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }

    interface Observer {
        void update(float temp, float humidity, float pressure);
    }

    static class DisplayObserver implements Observer {
        public void update(float temp, float humidity, float pressure) {
            System.out.println("Display: Temperature = " + temp + "C, Humidity = " + humidity + "%, Pressure = " + pressure + "hPa");
        }
    }

    static class LoggerObserver implements Observer {
        public void update(float temp, float humidity, float pressure) {
            System.out.println("Logger: Logging weather data - Temp: " + temp + ", Humidity: " + humidity + ", Pressure: " + pressure);
        }
    }

    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        weatherStation.registerObserver(new DisplayObserver());
        weatherStation.registerObserver(new LoggerObserver());

        weatherStation.setMeasurements(25.5f, 65.0f, 1013.25f);
        weatherStation.setMeasurements(26.2f, 70.0f, 1012.0f);
    }
}