package _14_SpringAdvancedQueryEx.service;

import _14_SpringAdvancedQueryEx.model.entity.AgeRestriction;
import _14_SpringAdvancedQueryEx.model.entity.Book;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);


    List<String> findByAgeRestriction(String ageR);

    List<String> findGoldenBooks(int copies);

    List<Book> selectBooksPrice5To40(BigDecimal above, BigDecimal below);

    List<Book> selectBooksNotReleasedInYear(String year);

    List<Book> selectBooksReleasedBefore(LocalDate releasedBefore);

    List<Book> selectBooksByString(String contains);

    int countBooksByTitleLength(int titleLength);
}
