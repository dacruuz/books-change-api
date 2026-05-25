package br.com.bookschange.api.application.book.ports.out;

import br.com.bookschange.api.domain.Book;

public interface CreateBookPortOut {
    Book create(Book book);
}
