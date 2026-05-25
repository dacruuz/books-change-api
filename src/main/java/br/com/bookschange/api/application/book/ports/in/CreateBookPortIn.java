package br.com.bookschange.api.application.book.ports.in;

import br.com.bookschange.api.application.book.adapters.in.dtos.request.CreateBookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.response.CreateBookResponse;

public interface CreateBookPortIn {
    CreateBookResponse create(CreateBookRequest request);
}
