package br.com.bookschange.api.application.bookcategory.ports.out;

import br.com.bookschange.api.domain.models.BookCategory;

import java.util.List;
import java.util.UUID;

public interface FindBookCategoryPortOut {
    List<BookCategory> findAllByCategoryUuid(UUID categoryUuid);
}
