package br.com.bookschange.api.application.store.ports.out;

import br.com.bookschange.api.domain.models.Store;

import java.util.Optional;
import java.util.UUID;

public interface FindStorePortOut {
    Optional<Store> findByUuid(UUID uuid);
    Optional<Store> findBySlug(String normalizedSlug);

    boolean existsByCnpj(String normalizedCnpj);
    boolean existsByEmail(String normalizedEmail);
    boolean existsBySlug(String normalizedSlug);
}
