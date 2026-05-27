package br.com.bookschange.api.application.book.ports.out;

import br.com.bookschange.api.domain.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindPagedBooksPortOut {
    Page<Book> findAllPaged(Pageable pageable);
}
