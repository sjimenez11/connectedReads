package saraymarcos.ProyectSpringBoot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import saraymarcos.ProyectSpringBoot.models.Book;
import saraymarcos.ProyectSpringBoot.services.*;

@SpringBootApplication
public class ProyectSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectSpringBootApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(InitialDataCreationService service) {
		return args -> {
			service.createFakeBooks(40);
			service.createFakeLibraries(10);
			service.createFakeUsers(10);
			service.createFakeReadingGroups(5);
		};
	}
}