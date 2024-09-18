package com.designPatterns.adapter;

public class DataSourceAdapterExercise {
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

    public static void main(String[] args) {
        CSVDataSource csvSource = new CSVDataSource();
        CommonDataInterface adapter = new CSVAdapter(csvSource);
        System.out.println(adapter.getData());
    }
}