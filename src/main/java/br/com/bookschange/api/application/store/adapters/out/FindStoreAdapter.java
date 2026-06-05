package br.com.bookschange.api.application.store.adapters.out;

import br.com.bookschange.api.application.store.adapters.out.repositories.StoreJpaRepository;
import br.com.bookschange.api.application.store.ports.out.FindStorePortOut;
import br.com.bookschange.api.domain.models.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindStoreAdapter implements FindStorePortOut {

    private final StoreJpaRepository repository;

    @Override
    public Optional<Store> findByUuid(UUID uuid) {
        return repository.findById(uuid);
    }

    @Override
    public Optional<Store> findBySlug(String slug) {
        return repository.findBySlug(slug);
    }

    @Override
    public Optional<Store> findByOwnerUuid(UUID ownerUuid) { return repository.findByOwnerUuid(ownerUuid); }

    @Override
    public boolean existsByCnpj(String cnpj) {
        return repository.existsByCnpj(cnpj);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByCommercialEmail(email);
    }

    @Override
    public boolean existsBySlug(String slug) {
        return repository.existsBySlug(slug);
    }
}
