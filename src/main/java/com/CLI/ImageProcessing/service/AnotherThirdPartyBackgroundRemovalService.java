package com.CLI.ImageProcessing.service;

import com.CLI.ImageProcessing.Adapter.BackgroundRemovalService;

public class AnotherThirdPartyBackgroundRemovalService implements BackgroundRemovalService {

    @Override
    public void removeBackground(String imagePath) {
        System.out.println("Removing background using Third Party Service 2 for " + imagePath);
    }
}
