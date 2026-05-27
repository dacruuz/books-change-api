package br.com.bookschange.api.application.book.adapters.out;

import br.com.bookschange.api.application.book.adapters.out.repositories.BookJpaRepository;
import br.com.bookschange.api.application.book.ports.out.UpdateBookPortOut;
import br.com.bookschange.api.domain.models.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateBookAdapter implements UpdateBookPortOut {

    private final BookJpaRepository repository;

    @Override
    public Book update(Book book) {
        return repository.save(book);
    }
}
