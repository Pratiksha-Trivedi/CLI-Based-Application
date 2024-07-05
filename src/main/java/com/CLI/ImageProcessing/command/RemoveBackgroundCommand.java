package com.CLI.ImageProcessing.command;

import com.CLI.ImageProcessing.Adapter.BackgroundRemovalService;

public class RemoveBackgroundCommand implements Command {
    private final String imageName;
    private final BackgroundRemovalService backgroundRemovalService;

    public RemoveBackgroundCommand(String imageName, BackgroundRemovalService backgroundRemovalService) {
        this.imageName = imageName;
        this.backgroundRemovalService = backgroundRemovalService;
    }

    @Override
    public void execute() {
        backgroundRemovalService.removeBackground(imageName);
    }
}
