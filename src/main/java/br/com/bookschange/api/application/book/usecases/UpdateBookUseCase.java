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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateBookUseCase implements UpdateBookPortIn {

    private final UpdateBookPortOut updateBookPortOut;
    private final FindBookPortOut findBookPortOut;
    private final BookMapper mapper;

    @Override
    public BookResponse update(UUID uuid, BookRequest request) {
        log.info("Buscando livro para edição | uuid: {}", uuid);

        Book foundBook = findBookPortOut.findByUuidOrThrow(uuid);

        mapper.updateBookFromRequest(request, foundBook);

        Book savedBook = updateBookPortOut.update(foundBook);

        log.info("Edição de livro feita com sucesso");
        return mapper.toBookResponse(savedBook);
    }
}
