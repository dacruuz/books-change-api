package br.com.bookschange.application.features.books.adapters.out;

import br.com.bookschange.application.domain.Book;
import br.com.bookschange.application.features.books.ports.out.CreateBookPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateBookAdapter implements CreateBookPortOut {

    private final CreateBookPortOut createBookPortOut;

    @Override
    public Book create(Book book) {
        return null;
    }
}
