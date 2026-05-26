package br.com.bookschange.api.application.book.adapters.out.repositories;

import br.com.bookschange.api.domain.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookJpaRepository extends JpaRepository<Book, UUID> {
}
