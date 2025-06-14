package org.example.techparanoiaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TechParanoiaServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(TechParanoiaServerApplication.class, args);
	}
}
