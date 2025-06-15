package org.example.techparanoiaserver;

import org.example.techparanoiaserver.entity.user.role.Role;
import org.example.techparanoiaserver.repository.user.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TechParanoiaServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(TechParanoiaServerApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RoleRepository repository){
		return args -> {
			if (repository.findByName("USER").isEmpty()){
				repository.save(
						Role.builder().name("USER").build()
				);
			}
		};
	}
}
