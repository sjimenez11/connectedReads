package saraymarcos.ProyectSpringBoot.services;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;
import saraymarcos.ProyectSpringBoot.dtos.user.UserDtoCreate;
import saraymarcos.ProyectSpringBoot.models.Book;
import saraymarcos.ProyectSpringBoot.models.Role;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
public class DataInsertionService {

    Faker faker = new Faker(new Locale("en-US"));

    public void insertUsers(UserService service, int amount) {
        if (amount <= 0) return;
        // One admin
        UserDtoCreate admin = new UserDtoCreate(
                "user",
                "user",
                Role.ADMIN
        );
        service.create(admin);

        // n-1 users
        for (int i = 0; i < amount-1; i++) {
            UserDtoCreate user = new UserDtoCreate(
                    faker.internet().safeEmailAddress(),
                    faker.internet().password(),
                    Role.USER
            );
            service.create(user);
        }
    }

    public void createFakeBooks(BookService bookService, int number){
        if(number <= 0) return;
        for(int i = 0; i < number; i++){
            Book book = new Book(
                    null,
                    //generateRandomIsbn(),
                    faker.number().digits(13),
                    faker.book().title(),
                    faker.book().author(),
                    //Math.round((10 + Math.random() * 21) * 100.0) / 100.0,
                    faker.number().randomDouble(2, 10, 30),
                    faker.book().genre(),
                    //como no hay un faker que cree una synopsis, hemos creado un método que contenga varias y ponga una aleatoriamente (random)
                    //generateRandomSynopsis(),
                    faker.lorem().sentence(10, 10),
                    //(long) (Math.random() * 101),
                    (long) faker.number().numberBetween(0, 100),
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );
            bookService.save(book);
        }
    }

    public static String generateRandomIsbn(){
        StringBuilder isbn = new StringBuilder();
        for(int i = 0; i < 13; i++){
            isbn.append((int) (Math.random() * 10));
        }
        return isbn.toString();
    }

    public static String generateRandomSynopsis(){
        String[] possibleSynopsis = {
                "Una historia emocionante que te mantendrá en vilo hasta la última página.",
                "Aventuras épicas en un mundo lleno de magia y peligros.",
                "Un relato conmovedor sobre el poder del amor y la amistad.",
                "Intriga y misterio se entrelazan en esta apasionante novela.",
                "Una novela que te hará reflexionar sobre la vida y el destino.",
                "Secretos oscuros acechan en cada esquina de este oscuro thriller."
        };
        int randomIndex = (int) (Math.random() * possibleSynopsis.length);
        return possibleSynopsis[randomIndex];
    }
}