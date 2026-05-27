package br.com.bookschange.api.application.book.usecases;

import br.com.bookschange.api.application.book.adapters.in.dtos.request.BookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.response.BookResponse;
import br.com.bookschange.api.application.book.mappers.BookMapper;
import br.com.bookschange.api.application.book.ports.in.CreateBookPortIn;
import br.com.bookschange.api.application.book.ports.out.CreateBookPortOut;
import br.com.bookschange.api.domain.models.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateBookUseCase implements CreateBookPortIn {

    private final CreateBookPortOut createBookPortOut;
    private final BookMapper mapper;

    @Override
    public BookResponse create(BookRequest request) {
        Book book = mapper.bookRequestToEntity(request);
        Book createdBook = createBookPortOut.create(book);

        return mapper.toBookResponse(createdBook);
    }
}
