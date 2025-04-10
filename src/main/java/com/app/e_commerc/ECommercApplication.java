package com.app.e_commerc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ECommercApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommercApplication.class, args);
	}

}
