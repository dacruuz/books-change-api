package br.com.bookschange.application.features.books.ports.in;

import br.com.bookschange.application.features.books.adapters.in.dtos.request.CreateBookRequest;
import br.com.bookschange.application.features.books.adapters.in.dtos.response.CreateBookResponse;

public interface CreateBookPortIn {
    CreateBookResponse create(CreateBookRequest request);
}
