package br.com.bookschange.api.application.bookcategory.adapters.out.repositories;

import br.com.bookschange.api.domain.models.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookCategoryJpaRepository extends JpaRepository<BookCategory, UUID> {
    List<BookCategory> findAllByCategoryUuid(UUID categoryUuid);
}
