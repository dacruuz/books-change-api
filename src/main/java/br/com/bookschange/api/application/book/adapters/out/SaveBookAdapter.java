package br.com.bookschange.api.application.book.adapters.out;

import br.com.bookschange.api.application.book.adapters.out.repositories.BookJpaRepository;
import br.com.bookschange.api.domain.models.Book;
import br.com.bookschange.api.application.book.ports.out.SaveBookPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveBookAdapter implements SaveBookPortOut {

    private final BookJpaRepository repository;

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }
}
