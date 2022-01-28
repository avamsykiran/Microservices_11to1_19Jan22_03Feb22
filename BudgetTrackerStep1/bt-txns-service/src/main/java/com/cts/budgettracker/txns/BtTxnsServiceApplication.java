package com.cts.budgettracker.txns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BtTxnsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtTxnsServiceApplication.class, args);
	}

}
