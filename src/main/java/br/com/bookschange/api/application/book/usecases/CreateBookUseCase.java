package br.com.bookschange.api.application.book.usecases;

import br.com.bookschange.api.application.book.adapters.in.dtos.request.BookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.response.BookResponse;
import br.com.bookschange.api.application.book.mappers.BookMapper;
import br.com.bookschange.api.application.book.ports.in.CreateBookPortIn;
import br.com.bookschange.api.application.book.ports.out.CreateBookPortOut;
import br.com.bookschange.api.application.user.ports.out.FindUserPortOut;
import br.com.bookschange.api.domain.models.Book;
import br.com.bookschange.api.domain.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateBookUseCase implements CreateBookPortIn {

    private final CreateBookPortOut createBookPortOut;
    private final FindUserPortOut findUserPortOut;
    private final BookMapper mapper;

    @Override
    public BookResponse create(BookRequest request) {
        log.info("Criando livro | titulo: {}", request.name());

        User owner = findUserPortOut.findByUuidOrThrow(request.ownerUuid());

        Book book = mapper.bookRequestToEntity(request);
        book.setOwner(owner);

        Book createdBook = createBookPortOut.create(book);

        log.info("Livro criado com sucesso | uuid: {} | título: {}", createdBook.getUuid(), createdBook.getName());
        return mapper.toBookResponse(createdBook);
    }
}
