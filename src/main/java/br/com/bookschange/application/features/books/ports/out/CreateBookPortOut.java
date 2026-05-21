package br.com.bookschange.application.features.books.ports.out;

import br.com.bookschange.application.domain.Book;

public interface CreateBookPortOut {
    Book create(Book book);
}
