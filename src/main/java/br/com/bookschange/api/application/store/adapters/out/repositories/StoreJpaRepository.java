package br.com.bookschange.api.application.store.adapters.out.repositories;

import br.com.bookschange.api.domain.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StoreJpaRepository extends JpaRepository<Store, UUID> {
    Optional<Store> findBySlug(String slug);
    Optional<Store> findByOwnerUuid(UUID ownerUuid);

    boolean existsByCnpj(String cnpj);
    boolean existsByCommercialEmail(String email);
    boolean existsBySlug(String slug);
}
