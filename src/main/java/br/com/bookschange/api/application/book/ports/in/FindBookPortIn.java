package br.com.bookschange.api.application.book.ports.in;

import br.com.bookschange.api.application.book.adapters.in.dtos.response.BookResponse;

import java.util.UUID;

public interface FindBookPortIn {
    BookResponse findByUuid(UUID uuid);
}
