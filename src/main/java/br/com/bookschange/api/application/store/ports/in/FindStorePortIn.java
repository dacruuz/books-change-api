package br.com.bookschange.api.application.store.ports.in;

import br.com.bookschange.api.application.store.adapters.in.dtos.response.StoreResponse;

import java.util.UUID;

public interface FindStorePortIn {
    StoreResponse findByUuid(UUID uuid);
}
