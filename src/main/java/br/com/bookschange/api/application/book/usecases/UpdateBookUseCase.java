package br.com.bookschange.api.application.book.usecases;

import br.com.bookschange.api.application.book.adapters.in.dtos.request.BookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.response.BookResponse;
import br.com.bookschange.api.application.book.mappers.BookMapper;
import br.com.bookschange.api.application.book.ports.in.UpdateBookPortIn;
import br.com.bookschange.api.application.book.ports.out.FindBookPortOut;
import br.com.bookschange.api.application.book.ports.out.UpdateBookPortOut;
import br.com.bookschange.api.domain.exceptions.NotFoundException;
import br.com.bookschange.api.domain.models.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateBookUseCase implements UpdateBookPortIn {

    private final UpdateBookPortOut updateBookPortOut;
    private final FindBookPortOut findBookPortOut;
    private final BookMapper mapper;

    @Override
    public BookResponse update(UUID uuid, BookRequest request) {
        findBookPortOut.findByUuid(uuid).orElseThrow(
                () -> new NotFoundException("Livro não encontrado")
        );
        Book foundBook;
        foundBook = mapper.bookRequestToEntity(request);
        Book savedBook = updateBookPortOut.update(foundBook);

        return mapper.toBookResponse(savedBook);
    }
}
