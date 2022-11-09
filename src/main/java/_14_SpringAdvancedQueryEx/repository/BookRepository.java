package _14_SpringAdvancedQueryEx.repository;

import _14_SpringAdvancedQueryEx.model.entity.AgeRestriction;
import _14_SpringAdvancedQueryEx.model.entity.Book;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);


    List<Book> findByAgeRestriction(AgeRestriction age);

    List<Book> findByPriceGreaterThanEqualOrPriceLessThanEqual(BigDecimal below, BigDecimal above);

    List<Book> findByCopiesIsLessThan(int copies);

    @Query("SELECT b FROM Book b " +
            "WHERE b.releaseDate not like CONCAT(:year,'%') ")
    List<Book> findByReleaseDateNotContains(String year);

    List<Book> findByReleaseDateBefore(LocalDate releasedBefore);

    List<Book> findByTitleContains(String contains);

    @Query("SELECT count(b) FROM Book b " +
            "WHERE LENGTH(b.title) > :titleLength")
    int findByTitle(int titleLength);
}
