package com.example.Bm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableCaching
@RestController
public class BmApplication {

	public static void main(String[] args) {

		SpringApplication.run(BmApplication.class, args);
	}

	@GetMapping("/log")
	public String home() {
		return "hello world";
	}

}
