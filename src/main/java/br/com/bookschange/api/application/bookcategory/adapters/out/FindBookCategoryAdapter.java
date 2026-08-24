package br.com.bookschange.api.application.bookcategory.adapters.out;

import br.com.bookschange.api.application.bookcategory.adapters.out.repositories.BookCategoryJpaRepository;
import br.com.bookschange.api.application.bookcategory.ports.out.FindBookCategoryPortOut;
import br.com.bookschange.api.domain.models.BookCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindBookCategoryAdapter implements FindBookCategoryPortOut {

    private final BookCategoryJpaRepository repository;

    @Override
    public List<BookCategory> findAllByCategoryUuid(UUID categoryUuid) {
        return repository.findAllByCategoryUuid(categoryUuid);
    }
}
