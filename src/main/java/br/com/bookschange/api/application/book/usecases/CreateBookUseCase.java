package br.com.bookschange.api.application.book.usecases;

import br.com.bookschange.api.application.book.adapters.in.dtos.request.BookRequest;
import br.com.bookschange.api.application.book.adapters.in.dtos.response.BookResponse;
import br.com.bookschange.api.application.book.mappers.BookMapper;
import br.com.bookschange.api.application.book.ports.in.CreateBookPortIn;
import br.com.bookschange.api.application.book.ports.out.CreateBookPortOut;
import br.com.bookschange.api.application.category.ports.out.FindCategoryPortOut;
import br.com.bookschange.api.application.user.ports.out.FindUserPortOut;
import br.com.bookschange.api.domain.models.Book;
import br.com.bookschange.api.domain.models.Category;
import br.com.bookschange.api.domain.models.User;
import br.com.bookschange.infrastructure.shared.util.DateUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateBookUseCase implements CreateBookPortIn {

    private final BookMapper mapper;
    private final CreateBookPortOut createBookPortOut;
    private final FindUserPortOut findUserPortOut;
    private final FindCategoryPortOut findCategoryPortOut;

    @Override
    @Transactional
    public BookResponse create(BookRequest request) {
        log.info("Criando livro | titulo: {}", request.name());

        User owner = findUserPortOut.findByUuidOrThrow(request.ownerUuid());
        List<Category> categories = findCategoryPortOut.findAllByUuids(request.categories());

        Book book = mapper.bookRequestToEntity(request);
        book.setOwner(owner);
        book.addCategories(categories);

        Book createdBook = createBookPortOut.create(book);

        log.info("Livro criado com sucesso | uuid: {} | título: {}", createdBook.getUuid(), createdBook.getName());
        return mapper.toBookResponse(createdBook);
    }
}
