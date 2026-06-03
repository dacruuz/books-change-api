package br.com.bookschange.api.application.store.ports.out;

import br.com.bookschange.api.domain.models.Store;

public interface DeleteStorePortOut {
    void delete(Store store);
}
