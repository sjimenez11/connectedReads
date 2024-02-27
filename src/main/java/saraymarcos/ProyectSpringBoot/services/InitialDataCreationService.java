package saraymarcos.ProyectSpringBoot.services;


import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;
import saraymarcos.ProyectSpringBoot.dtos.user.UserDtoCreate;
import saraymarcos.ProyectSpringBoot.models.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class InitialDataCreationService {
    private final BookService bookService;
    private final UserService userService;
    private final ReadingGroupService readingGroupService;
    private final LibraryService libraryService;
    private final Faker faker = new Faker(new Locale("es-ES"));

    public void createFakeUsers(int amount) {
        if (amount <= 0) return;
        // One admin
        UserDtoCreate admin = new UserDtoCreate(
                "user",
                "user",
                Role.ADMIN
        );
        userService.create(admin);

        // n-1 users
        for (int i = 0; i < amount-1; i++) {
            UserDtoCreate user = new UserDtoCreate(
                    faker.internet().safeEmailAddress(),
                    faker.internet().password(),
                    Role.USER
            );
            userService.create(user);
        }
    }

    public void createFakeBooks(int number){
        if(number <= 0) return;
        for(int i = 0; i < number; i++){
            Book book = new Book(
                    null,
                    generateRandomIsbn(),
                    faker.book().title(),
                    faker.book().author(),
                    Math.round((10 + Math.random() * 21) * 100.0) / 100.0, //Entre 10 y 30€ con dos decimales
                    faker.book().genre(),
                    //como no hay un faker que cree una synopsis, hemos creado un método que contenga varias y ponga una aleatoriamente (random)
                    generateRandomSynopsis(),
                    generateRandomClassification(),
                    (long) (Math.random() * 101),
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );
            bookService.save(book);
        }
    }

    public void createFakeBooks2(BookService bookService, int number){
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
                    faker.lorem().sentence(1, 2),
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

    public static String generateRandomClassification(){
        String[] possibleSynopsis = {
                "Want to Read",
                "Currently Reading",
                "Read"
        };
        int randomIndex = (int) (Math.random() * possibleSynopsis.length);
        return possibleSynopsis[randomIndex];
    }

    public void createFakeReadingGroups(int number){
        if (number <= 0) return;
        for(int i = 0; i < number; i++){
            ReadingGroup readingGroup = new ReadingGroup(
                    null,
                    faker.lorem().sentence(1, 5),
                    faker.lorem().sentence(10, 10),
                    faker.lorem().sentence(1, 2)
            );
            readingGroupService.save(readingGroup);
        }
    }

    public void createFakeLibraries(int number){
        if (number <= 0) return;
        //long id = 1;
        //List<Book> books = Collections.singletonList(bookService.findById(id));
        List<Book> books = bookService.findAll();

        for(int i = 0; i < number; i++){
            int bookIndex = faker.number().numberBetween(0, books.size());
            //Book book = books.get(bookIndex);
            Library library = new Library(
                    null,
                    books
            );
            libraryService.save(library);
        }
    }


}
