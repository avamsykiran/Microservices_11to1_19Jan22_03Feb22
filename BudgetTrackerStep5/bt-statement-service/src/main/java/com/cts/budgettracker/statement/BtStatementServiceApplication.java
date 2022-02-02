package com.cts.budgettracker.statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class BtStatementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtStatementServiceApplication.class, args);
	}

}
