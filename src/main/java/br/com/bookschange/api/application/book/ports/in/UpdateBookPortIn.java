package br.com.bookschange.api.application.book.ports.in;

import br.com.bookschange.api.application.book.adapters.in.dtos.request.CreateBookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.request.UpdateBookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.response.BookResponse;

import java.util.UUID;

public interface UpdateBookPortIn {
    BookResponse update(UUID uuid, UpdateBookRequest request);
}
