package _14_SpringAdvancedQueryEx;

import _14_SpringAdvancedQueryEx.model.entity.Author;
import _14_SpringAdvancedQueryEx.model.entity.AuthorNamesWithTotalCopies;
import _14_SpringAdvancedQueryEx.model.entity.Book;
import _14_SpringAdvancedQueryEx.service.AuthorService;
import _14_SpringAdvancedQueryEx.service.BookService;
import _14_SpringAdvancedQueryEx.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public Runner(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        //1. Books Titles by Age Restriction

//        System.out.println("Input age restriction:");
//        String ageR = scanner.nextLine().toUpperCase(Locale.ROOT);
//
//        this.bookService.findByAgeRestriction(ageR)
//                .forEach(System.out::println);


        //2. Golden Books

//        System.out.println("Insert number of copies for Gold:");
//        int copies = scanner.nextInt();
//        this.bookService.findGoldenBooks(copies)
//                .forEach(System.out::println);


        //3. Books by Price


//        this.bookService.selectBooksPrice5To40(BigDecimal.valueOf(40), BigDecimal.valueOf(5))
//                .forEach(b -> {
//                    System.out.printf("%s - $%.2f%n", b.getTitle(), b.getPrice());
//                });


        //4. Not Released Books
//        this.bookService.selectBooksNotReleasedInYear("1998")
//                .forEach(book -> {
//                    System.out.println(book.getTitle());
//                });

        //5. Books Released Before Date
//        this.bookService.selectBooksReleasedBefore(LocalDate.of(1989,12,30))
//                .forEach(book -> {
//                    System.out.printf("%s %s %.2f%n", book.getTitle(), book.getEditionType(), book.getPrice());
//                });

        //6. Authors Search

//        this.authorService.selectAuthorsFirstNameEndsWith("dy")
//                .forEach(author ->
//                        System.out.printf("%s %s%n",
//                                author.getFirstName(),
//                                author.getLastName()));

        // 7. Book Search
//        this.bookService.selectBooksByString(String.format("WOR").toLowerCase())
//                .forEach(book -> {
//                    System.out.println(book.getTitle());
//                });

        //9. Count Books
//        System.out.println("Type length of book Title");
//        int lengthInput = scanner.nextInt();
//        int numOfBooks = this.bookService.countBooksByTitleLength(lengthInput);
//
//        System.out.printf("There are %d books with longer title than %d symbol%n", numOfBooks, lengthInput);

        //10. Total Book Copies
        this.authorService.selectBookCopies()
                .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName() + " " + a.getTotalCopies()));



    }
//        seedData();
//
//
//        pritnALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");
//
//    }
//
//    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
//        bookService
//                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
//                .forEach(System.out::println);
//    }
//
//    private void printAllAuthorsAndNumberOfTheirBooks() {
//        authorService
//                .getAllAuthorsOrderByCountOfTheirBooks()
//                .forEach(System.out::println);
//    }
//
//    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
//        bookService
//                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
//                .forEach(System.out::println);
//    }
//
//    private void printAllBooksAfterYear(int year) {
//        bookService
//                .findAllBooksAfterYear(year)
//                .stream()
//                .map(Book::getTitle)
//                .forEach(System.out::println);
//    }
//
//    private void seedData() throws IOException {
//        categoryService.seedCategories();
//        authorService.seedAuthors();
//        bookService.seedBooks();
//    }
}
