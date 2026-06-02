package br.com.bookschange.api.application.store.adapters.out.repositories;

import br.com.bookschange.api.domain.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreJpaRepository extends JpaRepository<Store, UUID> {
}
