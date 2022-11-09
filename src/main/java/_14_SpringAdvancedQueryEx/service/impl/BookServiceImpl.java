package _14_SpringAdvancedQueryEx.service.impl;

import _14_SpringAdvancedQueryEx.model.entity.*;
import _14_SpringAdvancedQueryEx.repository.BookRepository;
import _14_SpringAdvancedQueryEx.service.AuthorService;
import _14_SpringAdvancedQueryEx.service.BookService;
import _14_SpringAdvancedQueryEx.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
       return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findByAgeRestriction(String ageR) {
        return this.bookRepository.findByAgeRestriction(AgeRestriction.valueOf(ageR))
                .stream()
                .map(Book::getTitle)
                .toList();
    }

    @Override
    public List<String> findGoldenBooks(int copies) {
        return this.bookRepository.findByCopiesIsLessThan(copies)
                .stream()
                .map(Book::getTitle)
                .toList();
    }

    @Override
    public List<Book> selectBooksPrice5To40(BigDecimal above, BigDecimal below) {
        return this.bookRepository.findByPriceGreaterThanEqualOrPriceLessThanEqual(above, below);
    }

    @Override
    public List<Book> selectBooksNotReleasedInYear(String year) {
        return this.bookRepository.findByReleaseDateNotContains(year);
    }

    @Override
    public List<Book> selectBooksReleasedBefore(LocalDate releasedBefore) {
        return this.bookRepository.findByReleaseDateBefore(releasedBefore);

    }

    @Override
    public List<Book> selectBooksByString(String contains) {
        return this.bookRepository.findByTitleContains(contains);
    }

    @Override
    public int countBooksByTitleLength(int titleLength) {
       return this.bookRepository.findByTitle(titleLength);
    }


    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}
