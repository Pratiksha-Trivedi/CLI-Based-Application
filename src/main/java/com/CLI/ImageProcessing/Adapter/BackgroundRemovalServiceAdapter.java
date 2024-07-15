package com.CLI.ImageProcessing.Adapter;

import com.CLI.ImageProcessing.service.AnotherThirdPartyBackgroundRemovalService;
import com.CLI.ImageProcessing.service.ThirdPartyBackgroundRemovalService;

public class BackgroundRemovalServiceAdapter implements BackgroundRemovalService {

    private BackgroundRemovalService primaryService;
    private BackgroundRemovalService secondaryService;

    public BackgroundRemovalServiceAdapter() {
        this.primaryService = new ThirdPartyBackgroundRemovalService();
        this.secondaryService = new AnotherThirdPartyBackgroundRemovalService();
    }

    @Override
    public void removeBackground(String imagePath) {
        try {
            primaryService.removeBackground(imagePath);
        } catch (Exception e) {
            System.out.println("Primary service failed, switching to secondary service.");
            secondaryService.removeBackground(imagePath);
        }
    }
}
