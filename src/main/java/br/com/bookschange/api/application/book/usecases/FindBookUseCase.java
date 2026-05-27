package br.com.bookschange.api.application.book.usecases;

import br.com.bookschange.api.application.book.adapters.in.dtos.response.BookResponse;
import br.com.bookschange.api.application.book.mappers.BookMapper;
import br.com.bookschange.api.application.book.ports.in.FindBookPortIn;
import br.com.bookschange.api.application.book.ports.out.FindBookPortOut;
import br.com.bookschange.api.domain.exceptions.NotFoundException;
import br.com.bookschange.api.domain.models.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindBookUseCase implements FindBookPortIn {

    private final BookMapper mapper;
    private final FindBookPortOut findBookPortOut;

    @Override
    public BookResponse findByUuid(UUID uuid) {
        Book foundBook = findBookPortOut.findByUuid(uuid).orElseThrow(
                () -> new NotFoundException("Livro não encontrado.")
        );
        return mapper.toBookResponse(foundBook);
    }
}
