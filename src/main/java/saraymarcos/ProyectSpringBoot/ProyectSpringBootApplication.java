package saraymarcos.ProyectSpringBoot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import saraymarcos.ProyectSpringBoot.services.InitialDataCreationService;

@SpringBootApplication
public class ProyectSpringBootApplication {
	//JavaFaker
	@Bean
	public CommandLineRunner init(InitialDataCreationService service) {
		return args -> {
			service.createDefaultAdminUser();
			service.createFakeBooks(40);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ProyectSpringBootApplication.class, args);
	}


}
