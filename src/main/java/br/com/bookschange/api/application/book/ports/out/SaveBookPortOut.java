package br.com.bookschange.api.application.book.ports.out;

import br.com.bookschange.api.domain.models.Book;

import java.util.List;

public interface SaveBookPortOut {
    Book save(Book book);
    List<Book> saveAll(List<Book> books);
}
