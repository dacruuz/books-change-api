package br.com.bookschange.application.features.books.adapters.out.repositories;

import br.com.bookschange.application.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookJpaRepository extends JpaRepository<UUID, Book> {
}
