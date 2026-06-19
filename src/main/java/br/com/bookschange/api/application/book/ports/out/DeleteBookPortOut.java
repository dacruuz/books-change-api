package br.com.bookschange.api.application.book.ports.out;

import br.com.bookschange.api.domain.models.Book;

import java.util.List;

public interface DeleteBookPortOut {
    void delete(Book book);
    void deleteAll(List<Book> books);
}
