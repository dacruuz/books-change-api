package br.com.bookschange.api.application.store.adapters.out;

import br.com.bookschange.api.application.store.adapters.out.repositories.StoreJpaRepository;
import br.com.bookschange.api.application.store.ports.out.DeleteStorePortOut;
import br.com.bookschange.api.domain.models.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteStoreAdapter implements DeleteStorePortOut {

    private final StoreJpaRepository repository;

    @Override
    public void delete(Store store) {
        repository.delete(store);
    }
}
