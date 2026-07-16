package br.com.bookschange.api.application.user.usecases;

import br.com.bookschange.api.application.book.adapters.in.dtos.request.FilterBookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.response.BookResponse;
import br.com.bookschange.api.application.user.ports.in.FilterUserBooksPagedPortIn;
import br.com.bookschange.api.application.user.ports.out.FilterUserBooksPagedPortOut;
import br.com.bookschange.infrastructure.shared.pagination.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilterUserBooksPagedUseCase implements FilterUserBooksPagedPortIn {

    private FilterUserBooksPagedPortOut filterUserBooksPagedPortOut;

    @Override
    public PageDTO<BookResponse> filter(FilterBookRequest request, Pageable pageable) {
        

        return null;
    }
}
