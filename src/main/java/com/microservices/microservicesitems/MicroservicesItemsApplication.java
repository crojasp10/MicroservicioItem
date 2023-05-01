package com.microservices.microservicesitems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroservicesItemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesItemsApplication.class, args);
	}

}
