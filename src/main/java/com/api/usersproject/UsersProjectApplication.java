package com.api.usersproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class UsersProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersProjectApplication.class, args);
	}

	@GetMapping("/")
	public String index(){
		return "Documentação da API Restful";
	}

}
