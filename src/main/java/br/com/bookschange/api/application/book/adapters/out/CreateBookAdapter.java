package br.com.bookschange.api.application.book.adapters.out;

import br.com.bookschange.api.application.book.adapters.out.repositories.BookJpaRepository;
import br.com.bookschange.api.domain.Book;
import br.com.bookschange.api.application.book.ports.out.CreateBookPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateBookAdapter implements CreateBookPortOut {

    private final BookJpaRepository repository;

    @Override
    public Book create(Book book) {
        return repository.save(book);
    }
}
