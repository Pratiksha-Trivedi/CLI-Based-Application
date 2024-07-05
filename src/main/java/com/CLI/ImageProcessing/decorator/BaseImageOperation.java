package com.CLI.ImageProcessing.decorator;
public class BaseImageOperation implements ImageOperation {
    private String image;

    public BaseImageOperation(String image) {
        this.image = image;
    }

    @Override
    public void performOperation() {
    }
}