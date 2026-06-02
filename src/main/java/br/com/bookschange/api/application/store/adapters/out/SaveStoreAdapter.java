package br.com.bookschange.api.application.store.adapters.out;

import br.com.bookschange.api.application.store.adapters.out.repositories.StoreJpaRepository;
import br.com.bookschange.api.application.store.ports.out.SaveStorePortOut;
import br.com.bookschange.api.domain.models.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveStoreAdapter implements SaveStorePortOut {

    private final StoreJpaRepository repository;

    @Override
    public Store save(Store store) {
        return repository.save(store);
    }
}
