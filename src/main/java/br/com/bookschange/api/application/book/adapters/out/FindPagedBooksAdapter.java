package br.com.bookschange.api.application.book.adapters.out;

import br.com.bookschange.api.application.book.adapters.out.repositories.BookJpaRepository;
import br.com.bookschange.api.application.book.ports.out.FindPagedBooksPortOut;
import br.com.bookschange.api.domain.models.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindPagedBooksAdapter implements FindPagedBooksPortOut {

    private final BookJpaRepository repository;

    @Override
    public Page<Book> findAllPaged(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Book> findAllActivePaged(Pageable pageable) {
        return repository.findAllByActiveTrue(pageable);
    }
}
