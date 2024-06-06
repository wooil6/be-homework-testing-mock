package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HomeworkTestingMockApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeworkTestingMockApplication.class, args);
	}

}
