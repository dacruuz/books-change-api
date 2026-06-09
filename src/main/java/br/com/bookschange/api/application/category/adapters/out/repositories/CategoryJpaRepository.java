package br.com.bookschange.api.application.category.adapters.out.repositories;

import br.com.bookschange.api.domain.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryJpaRepository extends JpaRepository<Category, UUID> {
}
