package br.com.bookschange.api.application.bookcategory.ports.out;

import br.com.bookschange.api.domain.models.BookCategory;

import java.util.List;

public interface DeleteBookCategoryPortOut {
    void deleteAll(List<BookCategory> bookCategoryList);
}
