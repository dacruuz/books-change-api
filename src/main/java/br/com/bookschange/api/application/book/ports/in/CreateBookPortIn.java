package br.com.bookschange.api.application.book.ports.in;

import br.com.bookschange.api.application.book.adapters.in.dtos.request.CreateBookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.response.BookResponse;

public interface CreateBookPortIn {
    BookResponse create(CreateBookRequest request);
}
