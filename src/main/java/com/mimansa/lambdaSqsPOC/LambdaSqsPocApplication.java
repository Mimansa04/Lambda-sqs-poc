package com.mimansa.lambdaSqsPOC;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.mimansa.lambdaSqsPOC.service.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
@Component("lambdaSqsPocApplication")
public class LambdaSqsPocApplication{

	s3FileDownloader s3FileDownloader = new s3FileDownloader();
//	@Bean
//	public Function<S3Event,String> invoke(){
//		System.out.println("Hello! Lambda is working in invoke!");
//		return (S3Event)->s3FileDownloader.initiateApplication(S3Event);
//	}

//public Function<S3EventNotification,String> invoke() {
//	System.out.println("Hello! Lambda is working in invoke!");
//	return (S3EventNotification)->apply(S3EventNotification);
//
//}

	@Bean
	public Consumer<S3Event> invoke() {
		System.out.println("Hello! Lambda is working in apply!");
		return this::initiateApp;
	}

	public void initiateApp(S3Event s3Event){
		S3Object fileName = s3FileDownloader.getFileName(s3Event);
		try {
			s3FileDownloader.fileReader(fileName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] args) {

	}



}
