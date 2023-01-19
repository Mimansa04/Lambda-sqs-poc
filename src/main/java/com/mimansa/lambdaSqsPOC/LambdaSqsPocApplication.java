package com.mimansa.lambdaSqsPOC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class LambdaSqsPocApplication {

	@Bean
	public Function<String,String> invoke(){
		System.out.println("Hello! Lambda is working!");
		return (String) -> null;
	}

	public static void main(String[] args) {
		SpringApplication.run(LambdaSqsPocApplication.class, args);
	}

}
