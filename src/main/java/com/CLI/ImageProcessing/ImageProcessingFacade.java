package com.CLI.ImageProcessing;

import com.CLI.ImageProcessing.command.Command;
import com.CLI.ImageProcessing.observer.OperationObserver;

import java.util.ArrayList;
import java.util.List;

public class ImageProcessingFacade {
    private List<OperationObserver> observers = new ArrayList<>();

    public void addObserver(OperationObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OperationObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String operation, String status) {
        for (OperationObserver observer : observers) {
            observer.update(operation, status);
        }
    }

    public void executeCommand(Command command) {
        command.execute();
        notifyObservers(command.getClass().getSimpleName(), "Completed");
    }
}