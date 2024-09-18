package com.designPatterns.proxy;

public class FileDownloadProxyExercise {
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

    public static void main(String[] args) {
        FileDownloader proxy = new FileDownloadProxy();
        proxy.download("example.zip");
    }
}