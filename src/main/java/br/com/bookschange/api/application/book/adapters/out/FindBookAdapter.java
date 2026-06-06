package br.com.bookschange.api.application.book.adapters.out;

import br.com.bookschange.api.application.book.adapters.out.repositories.BookJpaRepository;
import br.com.bookschange.api.application.book.ports.out.FindBookPortOut;
import br.com.bookschange.api.domain.exceptions.NotFoundException;
import br.com.bookschange.api.domain.models.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindBookAdapter implements FindBookPortOut {

    private final BookJpaRepository repository;

    @Override
    public Optional<Book> findByUuid(UUID uuid) {
        return repository.findById(uuid);
    }

    @Override
    public Book findByUuidOrThrow(UUID uuid) {
        return repository.findById(uuid)
                .orElseThrow(() -> {
                    log.warn("Livro não encontrado | uuid: {}", uuid);
                    return new NotFoundException("Livro não encontrado");
                });
    }
}
