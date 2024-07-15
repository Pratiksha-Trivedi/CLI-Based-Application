package com.CLI.ImageProcessing.strategy;

public class GoogleDriveStorageStrategy implements StorageStrategy {

    @Override
    public void store(String destination, String source) {
        // Implementation to store in Google Drive
        System.out.println("Storing " + source + " to Google Drive at " + destination);
    }
}
