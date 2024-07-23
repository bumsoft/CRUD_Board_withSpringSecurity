package com.example.CRUD_Board_withSpringSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CrudBoardWithSpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudBoardWithSpringSecurityApplication.class, args);
	}

}
