package br.com.bookschange.api.application.book.usecases;

import br.com.bookschange.api.application.book.adapters.in.dtos.response.BookResponse;
import br.com.bookschange.api.application.book.mappers.BookMapper;
import br.com.bookschange.api.application.book.ports.in.FindPagedBookPortIn;
import br.com.bookschange.api.application.book.ports.out.FindPagedBooksPortOut;
import br.com.bookschange.api.domain.models.Book;
import br.com.bookschange.infrastructure.shared.pagination.PageDTO;
import br.com.bookschange.infrastructure.shared.pagination.PageMapper;
import br.com.bookschange.infrastructure.shared.pagination.PaginationFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindPagedBooksUseCase implements FindPagedBookPortIn {

    private final BookMapper bookMapper;
    private final PageMapper pageMapper;
    private final FindPagedBooksPortOut findPagedBooksPortOut;


    @Override
    public PageDTO<BookResponse> findAllPaged(int page, int pageSize) {
        Pageable pageable = PaginationFactory.createPageable(page, pageSize);

        Page<Book> books = findPagedBooksPortOut.findAllPaged(pageable);

        Page<BookResponse> mappedPage = books.map(bookMapper::toBookResponse);

        return pageMapper.toPageDTO(mappedPage);
    }
}
