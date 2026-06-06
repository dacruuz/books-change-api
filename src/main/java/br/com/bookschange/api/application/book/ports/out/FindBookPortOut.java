package br.com.bookschange.api.application.book.ports.out;

import br.com.bookschange.api.domain.models.Book;

import java.util.Optional;
import java.util.UUID;

public interface FindBookPortOut {
    Optional<Book> findByUuid(UUID uuid);
    Book findByUuidOrThrow(UUID uuid);
}
