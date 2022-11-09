package _14_SpringAdvancedQueryEx.service;

import _14_SpringAdvancedQueryEx.model.entity.Author;
import _14_SpringAdvancedQueryEx.model.entity.AuthorNamesWithTotalCopies;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();

    List<Author> selectAuthorsFirstNameEndsWith(String endWith);

    List<AuthorNamesWithTotalCopies> selectBookCopies();
}
