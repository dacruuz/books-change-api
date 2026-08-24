package br.com.bookschange.api.application.bookcategory.adapters.out.repositories;

import br.com.bookschange.api.domain.models.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookCategoryJpaRepository extends JpaRepository<BookCategory, UUID> {
}
