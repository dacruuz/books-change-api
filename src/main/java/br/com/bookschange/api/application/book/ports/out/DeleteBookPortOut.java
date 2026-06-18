package br.com.bookschange.api.application.book.ports.out;

import br.com.bookschange.api.domain.models.Book;

public interface DeleteBookPortOut {
    void delete(Book book);
}
