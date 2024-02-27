package saraymarcos.ProyectSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import saraymarcos.ProyectSpringBoot.models.Book;
import saraymarcos.ProyectSpringBoot.services.*;

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

	@Bean
	public CommandLineRunner init(DataInsertionService dataInsertionService, UserService userService, BookService bookService, LibraryService libraryService, ReadingGroupService readingGroupService) {
		return args -> {
			dataInsertionService.insertUsers(userService, 5);
			dataInsertionService.createFakeBooks(bookService, 5);
			dataInsertionService.createFakeLibraries(libraryService, 5);
			dataInsertionService.createFakeReadingGroups(readingGroupService, 3);
		};
	}
}
