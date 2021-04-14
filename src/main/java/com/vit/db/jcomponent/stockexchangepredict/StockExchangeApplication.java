package com.vit.db.jcomponent.stockexchangepredict;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.vit.db.jcomponent.stockexchangepredict")
public class StockExchangeApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(StockExchangeApplication.class, args);
	}

}
