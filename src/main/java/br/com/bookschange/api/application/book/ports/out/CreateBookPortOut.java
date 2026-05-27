package br.com.bookschange.api.application.book.ports.out;

import br.com.bookschange.api.domain.models.Book;

public interface CreateBookPortOut {
    Book create(Book book);
}
