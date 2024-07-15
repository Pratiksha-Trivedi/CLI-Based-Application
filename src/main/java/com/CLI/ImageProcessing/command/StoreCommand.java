package com.CLI.ImageProcessing.command;

import com.CLI.ImageProcessing.strategy.StorageStrategy;

public class StoreCommand implements Command {

    private StorageStrategy storageStrategy;
    private String destination;
    private String source;

    public StoreCommand(StorageStrategy storageStrategy, String destination, String source) {
        this.storageStrategy = storageStrategy;
        this.destination = destination;
        this.source = source;
    }

    @Override
    public void execute() {
        storageStrategy.store(destination, source);
    }
}
