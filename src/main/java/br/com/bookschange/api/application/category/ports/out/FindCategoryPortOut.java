package br.com.bookschange.api.application.category.ports.out;

import br.com.bookschange.api.domain.models.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FindCategoryPortOut {
    List<Category> findAll();

    Optional<Category> findByUuid(UUID uuid);
    Category findByUuidOrThrow(UUID uuid);
}
