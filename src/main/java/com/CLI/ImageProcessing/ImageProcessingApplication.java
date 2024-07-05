package com.CLI.ImageProcessing;

import com.CLI.ImageProcessing.Adapter.AIService;
import com.CLI.ImageProcessing.Adapter.AIServiceImpl;
import com.CLI.ImageProcessing.Adapter.BackgroundRemovalService;
import com.CLI.ImageProcessing.Adapter.BackgroundRemovalServiceAdapter;
import com.CLI.ImageProcessing.command.*;
import com.CLI.ImageProcessing.decorator.CommandLoggingDecorator;
import com.CLI.ImageProcessing.observer.ImageProcessingOperation;
import com.CLI.ImageProcessing.observer.OperationLogger;
import com.CLI.ImageProcessing.service.ThirdPartyBackgroundRemovalService;
import com.CLI.ImageProcessing.strategy.CreditCardPaymentStrategy;
import com.CLI.ImageProcessing.strategy.PayPalPaymentStrategy;
import com.CLI.ImageProcessing.strategy.PaymentContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageProcessingApplication {

	public static void main(String[] args) {

		ImageProcessingFacade facade = new ImageProcessingFacade();
		BackgroundRemovalService backgroundRemovalService = new BackgroundRemovalServiceAdapter(new ThirdPartyBackgroundRemovalService());

		AIService aiService = new AIServiceImpl();

		Command resizeCommand = new ResizeCommand("image.jpg", 800, 600);
		Command extendCommand = new ExtendCommand("image.jpg", "right", 300, aiService);
		Command cropCommand = new CropCommand("image.jpg", 10, 10, 50, 50);
		Command removeBgCommand = new RemoveBackgroundCommand("image.jpg", backgroundRemovalService);

		ImageProcessingOperation operation = new ImageProcessingOperation();
		operation.addObserver(new OperationLogger());

		CommandLoggingDecorator loggingResizeCommand = new CommandLoggingDecorator(resizeCommand);
		CommandLoggingDecorator loggingExtendCommand = new CommandLoggingDecorator(extendCommand);
		CommandLoggingDecorator loggingCropCommand = new CommandLoggingDecorator(cropCommand);
		CommandLoggingDecorator loggingRemoveBgCommand = new CommandLoggingDecorator(removeBgCommand);

		operation.executeOperation(loggingResizeCommand);
		operation.executeOperation(loggingExtendCommand);
		operation.executeOperation(loggingCropCommand);
		operation.executeOperation(loggingRemoveBgCommand);

		CompositeCommand compositeCommand = new CompositeCommand();
		System.out.println("Combining two operations.......");
		compositeCommand.addCommand(new RemoveBackgroundCommand("image.jpg", backgroundRemovalService));
		compositeCommand.addCommand(new ResizeCommand("image.jpg", 800, 600));
		facade.executeCommand(compositeCommand);

		Command storeGoogleDriveCommand = new StoreCommand("google-drive", "/MyImages/result.jpg", "image.jpg", "image.jpg");
		facade.executeCommand(storeGoogleDriveCommand);

		Command storeS3Command = new StoreCommand("aws-s3", "mybucket", "result.jpg", "image.jpg");
		facade.executeCommand(storeS3Command);

		PaymentContext paymentContext = new PaymentContext();
		paymentContext.setPaymentStrategy(new CreditCardPaymentStrategy());
		paymentContext.executePayment(50.00);

		paymentContext.setPaymentStrategy(new PayPalPaymentStrategy());
		paymentContext.executePayment(100.00);
	}
}
