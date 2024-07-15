package com.CLI.ImageProcessing;

import com.CLI.ImageProcessing.Adapter.AIService;
import com.CLI.ImageProcessing.Adapter.AIServiceImpl;
import com.CLI.ImageProcessing.Adapter.BackgroundRemovalService;
import com.CLI.ImageProcessing.Adapter.BackgroundRemovalServiceAdapter;
import com.CLI.ImageProcessing.command.*;
import com.CLI.ImageProcessing.decorator.CommandLoggingDecorator;
import com.CLI.ImageProcessing.observer.ImageProcessingOperation;
import com.CLI.ImageProcessing.observer.OperationLogger;
import com.CLI.ImageProcessing.strategy.*;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageProcessingApplication {

	public static void main(String[] args) {

		ImageProcessingFacade facade = new ImageProcessingFacade();
		BackgroundRemovalService backgroundRemovalService = new BackgroundRemovalServiceAdapter();
		AIService aiService = new AIServiceImpl();

		Command resizeCommand = new ResizeCommand("image.jpg", 800, 600);
		Command extendCommand = new ExtendCommand("image.jpg", "right", 300, aiService);
		Command cropCommand = new CropCommand("image.jpg", 10, 10, 50, 50);
		Command removeBgCommand = new RemoveBackgroundCommand("image.jpg", backgroundRemovalService);

		CompositeCommand compositeCommand = new CompositeCommand();
		compositeCommand.addCommand(resizeCommand);
		compositeCommand.addCommand(extendCommand);
		compositeCommand.addCommand(cropCommand);
		compositeCommand.addCommand(removeBgCommand);

		CommandLoggingDecorator loggingCompositeCommand = new CommandLoggingDecorator(compositeCommand);

		ImageProcessingOperation operation = new ImageProcessingOperation();
		operation.addObserver(new OperationLogger());

		operation.executeOperation(loggingCompositeCommand);

		StorageStrategy googleDriveStrategy = new GoogleDriveStorageStrategy();
		StorageStrategy awsS3Strategy = new AwsS3StorageStrategy();

		Command storeGoogleDriveCommand = new StoreCommand(googleDriveStrategy, "/MyImages/result.jpg", "image.jpg");
		facade.executeCommand(storeGoogleDriveCommand);

		Command storeS3Command = new StoreCommand(awsS3Strategy, "mybucket", "result.jpg");
		facade.executeCommand(storeS3Command);

		PaymentContext paymentContext = new PaymentContext();

		paymentContext.setPricingStrategy(new ResizePricingStrategy());
		paymentContext.setPaymentStrategy(new CreditCardPaymentStrategy());
		paymentContext.executePayment();

		paymentContext.setPricingStrategy(new ExtendPricingStrategy());
		paymentContext.setPaymentStrategy(new PayPalPaymentStrategy());
		paymentContext.executePayment();

		paymentContext.setPricingStrategy(new BackgroundRemovalPricingStrategy());
		paymentContext.setPaymentStrategy(new CreditCardPaymentStrategy());
		paymentContext.executePayment();
	}
}
