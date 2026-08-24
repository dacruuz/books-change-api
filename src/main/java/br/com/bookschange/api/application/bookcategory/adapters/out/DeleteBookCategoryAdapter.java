package br.com.bookschange.api.application.bookcategory.adapters.out;

import br.com.bookschange.api.application.bookcategory.adapters.out.repositories.BookCategoryJpaRepository;
import br.com.bookschange.api.application.bookcategory.ports.out.DeleteBookCategoryPortOut;
import br.com.bookschange.api.domain.models.BookCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteBookCategoryAdapter implements DeleteBookCategoryPortOut {

    private final BookCategoryJpaRepository repository;

    @Override
    public void deleteAll(List<BookCategory> bookCategoryList) {
        repository.deleteAll(bookCategoryList);
    }
}
