package com.designPatterns.strategy;

public class CompressionLibrary {
    private CompressionStrategy strategy;

    public void setStrategy(CompressionStrategy strategy) {
        this.strategy = strategy;
    }

    public void compressFile(String fileName) {
        strategy.compress(fileName);
    }

    interface CompressionStrategy {
        void compress(String fileName);
    }

    static class ZipCompression implements CompressionStrategy {
        public void compress(String fileName) {
            System.out.println("Compressing " + fileName + " using ZIP compression");
            // ZIP compression implementation
        }
    }

    static class GzipCompression implements CompressionStrategy {
        public void compress(String fileName) {
            System.out.println("Compressing " + fileName + " using GZIP compression");
            // GZIP compression implementation
        }
    }

    public static void main(String[] args) {
        CompressionLibrary library = new CompressionLibrary();

        library.setStrategy(new ZipCompression());
        library.compressFile("document.txt");

        library.setStrategy(new GzipCompression());
        library.compressFile("image.png");
    }
}
