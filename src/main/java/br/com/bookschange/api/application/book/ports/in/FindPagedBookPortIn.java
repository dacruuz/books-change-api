package br.com.bookschange.api.application.book.ports.in;

import br.com.bookschange.api.application.book.adapters.in.dtos.response.BookResponse;
import br.com.bookschange.infrastructure.shared.pagination.PageDTO;

public interface FindPagedBookPortIn {
    PageDTO<BookResponse> findAllPaged(int page, int pageSize);
}
