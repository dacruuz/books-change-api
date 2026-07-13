package br.com.bookschange.api.application.book.adapters.out;

import br.com.bookschange.api.application.book.adapters.out.repositories.BookJpaRepository;
import br.com.bookschange.api.application.book.ports.out.DeleteBookPortOut;
import br.com.bookschange.api.domain.models.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteBookAdapter implements DeleteBookPortOut {

    private final BookJpaRepository repository;

    @Override
    public void delete(Book book) { repository.delete(book); }

    @Override
    public void deleteAll(List<Book> books) {
        repository.deleteAll(books);
    }
}
