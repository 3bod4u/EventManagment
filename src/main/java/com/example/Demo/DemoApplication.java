package com.example.Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Random;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		String code = new BCryptPasswordEncoder().encode("12345678");
		System.out.println(code);


			String g = "ABD";
			StringBuilder aa = new StringBuilder();
			Random rnd = new Random();

			aa.append(rnd.nextFloat()*g.length());

			System.out.println(aa);

	}

	}
