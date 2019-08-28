package ch.matfly.suivirecherches;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SuiviRecherchesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuiviRecherchesApplication.class, args);
	}

}
