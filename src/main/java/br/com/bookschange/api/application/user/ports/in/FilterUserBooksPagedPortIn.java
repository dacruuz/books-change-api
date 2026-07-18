package br.com.bookschange.api.application.user.ports.in;

import br.com.bookschange.api.application.book.adapters.in.dtos.request.FilterBookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.response.BookResponse;
import br.com.bookschange.infrastructure.shared.pagination.PageDTO;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FilterUserBooksPagedPortIn {

    PageDTO<BookResponse> filter(UUID ownerUuid, FilterBookRequest request, int page, int pageSize);
}
