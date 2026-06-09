package br.com.bookschange.api.application.category.adapters.out;

import br.com.bookschange.api.application.category.adapters.out.repositories.CategoryJpaRepository;
import br.com.bookschange.api.application.category.ports.out.SaveCategoryPortOut;
import br.com.bookschange.api.domain.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveCategoryAdapter implements SaveCategoryPortOut {

    private final CategoryJpaRepository repository;

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }
}
