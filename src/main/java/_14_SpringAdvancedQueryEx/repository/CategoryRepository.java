package _14_SpringAdvancedQueryEx.repository;

import _14_SpringAdvancedQueryEx.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
