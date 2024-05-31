package com.quizz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizzServiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizzServiveApplication.class, args);
	}

}
