package br.com.bookschange.api.application.book.ports.in;

import java.util.UUID;

public interface DeleteBookPortIn {
    void delete(UUID uuid);
}
