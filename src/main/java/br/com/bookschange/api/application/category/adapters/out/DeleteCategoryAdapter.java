package br.com.bookschange.api.application.category.adapters.out;

import br.com.bookschange.api.application.category.adapters.out.repositories.CategoryJpaRepository;
import br.com.bookschange.api.application.category.ports.out.DeleteCategoryPortOut;
import br.com.bookschange.api.domain.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteCategoryAdapter implements DeleteCategoryPortOut {

    private final CategoryJpaRepository repository;

    @Override
    public void delete(Category category) {
        repository.delete(category);
    }
}
