package saraymarcos.ProyectSpringBoot.services;


import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;
import saraymarcos.ProyectSpringBoot.dtos.user.UserDtoCreate;
import saraymarcos.ProyectSpringBoot.models.Book;
import saraymarcos.ProyectSpringBoot.models.Role;
import saraymarcos.ProyectSpringBoot.models.User;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class InitialDataCreationService {
    private final BookService bookService;
    private final UserService userService;
    private final Faker faker = new Faker(new Locale("en-US"));

    public void createDefaultAdminUser(){
        UserDtoCreate user = new UserDtoCreate("user", "user", Role.ADMIN);
        userService.create(user);
    }

    public void createFakeBooks(int number){
        if(number <= 0) return;
        for(int i = 0; i < number; i++){
            Book book = new Book(
                    null,
                    generateRandomIsbn(),
                    faker.book().title(),
                    faker.book().author(),
                    (double) (Math.round((10 + Math.random() * 21) * 100) / 100), //Entre 10 y 30€ con dos decimales
                    faker.book().genre(),
                    //como no hay un faker que cree una synopsis, hemos creado un método que contenga varias y ponga una aleatoriamente (random)
                    generateRandomSynopsis(),
                    (long) (Math.random() * 101),
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
