package saraymarcos.ProyectSpringBoot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import saraymarcos.ProyectSpringBoot.models.Book;
import saraymarcos.ProyectSpringBoot.services.BookService;
import saraymarcos.ProyectSpringBoot.services.DataInsertionService;
import saraymarcos.ProyectSpringBoot.services.UserService;

@SpringBootApplication
public class ProyectSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProyectSpringBootApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(DataInsertionService dataInsertionService, UserService userService, BookService bookService) {
		return args -> {
			dataInsertionService.insertUsers(userService, 10);
			dataInsertionService.createFakeBooks(bookService, 20);
		};
	}
}
