package com.CLI.ImageProcessing.decorator;

import com.CLI.ImageProcessing.command.Command;

public class CommandLoggingDecorator implements Command, ImageOperation {
    private Command command;

    public CommandLoggingDecorator(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {

        command.execute();
    }

    @Override
    public void performOperation() {

        System.out.println("Performing operation: " + command.getClass().getSimpleName());
        command.execute();
    }

}
