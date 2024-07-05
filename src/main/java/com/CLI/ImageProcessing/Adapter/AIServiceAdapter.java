package com.CLI.ImageProcessing.Adapter;

import com.CLI.ImageProcessing.service.ThirdPartyAIService;

public class AIServiceAdapter implements AIService {
    private ThirdPartyAIService service;

    public AIServiceAdapter(ThirdPartyAIService service) {
        this.service = service;
    }

    @Override
    public void extendImage(String image, String direction, int pixels) {
        service.extendImage(image, direction, pixels);
    }
}