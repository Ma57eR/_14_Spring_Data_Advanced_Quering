package _14_SpringAdvancedQueryEx.repository;

import _14_SpringAdvancedQueryEx.model.entity.Author;
import _14_SpringAdvancedQueryEx.model.entity.AuthorNamesWithTotalCopies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a ORDER BY a.books.size DESC")
    List<Author> findAllByBooksSizeDESC();

    List<Author> findByFirstNameEndsWith(String endWith);

    @Query("select a.firstName as firstName, " +
            "a.lastName as lastName, " +
            "sum(b.copies) as totalCopies " +
            "FROM Author as a " +
            "JOIN a.books as b " +
            "GROUP BY b.author "+
            "ORDER BY totalCopies desc")
    List<AuthorNamesWithTotalCopies> findCopiesForAuthor();
}
