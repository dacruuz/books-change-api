package br.com.bookschange.api.application.category.adapters.out;

import br.com.bookschange.api.application.category.adapters.out.repositories.CategoryJpaRepository;
import br.com.bookschange.api.application.category.ports.out.FindCategoryPortOut;
import br.com.bookschange.api.domain.exceptions.NotFoundException;
import br.com.bookschange.api.domain.models.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindCategoryAdapter implements FindCategoryPortOut {

    private final CategoryJpaRepository repository;

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Category> findByUuid(UUID uuid) {
        return repository.findById(uuid);
    }

    @Override
    public Category findByUuidOrThrow(UUID uuid) {
        return repository.findById(uuid).orElseThrow(() -> {
            log.warn("Categoria não encontrada | uuid: {}", uuid);
            return new NotFoundException("Categoria não encontrada");
        });
    }

    @Override
    public boolean existsBySlug(String slug) {
        return repository.existsBySlug(slug);
    }
}
