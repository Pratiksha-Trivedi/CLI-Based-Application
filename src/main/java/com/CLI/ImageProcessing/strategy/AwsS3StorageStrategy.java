package com.CLI.ImageProcessing.strategy;

public class AwsS3StorageStrategy implements StorageStrategy {

    @Override
    public void store(String destination, String source) {
        // Implementation to store in AWS S3
        System.out.println("Storing " + source + " to AWS S3 bucket " + destination);
    }
}
