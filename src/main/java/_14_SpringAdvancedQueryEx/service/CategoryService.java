package _14_SpringAdvancedQueryEx.service;

import _14_SpringAdvancedQueryEx.model.entity.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> getRandomCategories();
}
