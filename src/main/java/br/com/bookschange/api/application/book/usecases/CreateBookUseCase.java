package br.com.bookschange.api.application.book.usecases;

import br.com.bookschange.api.application.book.adapters.in.dtos.request.CreateBookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.response.CreateBookResponse;
import br.com.bookschange.api.application.book.mappers.BookMapper;
import br.com.bookschange.api.application.book.ports.in.CreateBookPortIn;
import br.com.bookschange.api.application.book.ports.out.CreateBookPortOut;
import br.com.bookschange.api.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateBookUseCase implements CreateBookPortIn {

    private final CreateBookPortOut createBookPortOut;
    private final BookMapper mapper;

    @Override
    public CreateBookResponse create(CreateBookRequest request) {
        Book book = mapper.creatBookRequestToEntity(request);
        Book createdBook = createBookPortOut.create(book);

        return mapper.toCreateBookResponse(createdBook);
    }
}
