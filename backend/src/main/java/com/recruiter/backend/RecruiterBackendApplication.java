package com.recruiter.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RecruiterBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruiterBackendApplication.class, args);
	}

}
