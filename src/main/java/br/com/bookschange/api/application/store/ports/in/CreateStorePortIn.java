package br.com.bookschange.api.application.store.ports.in;

import br.com.bookschange.api.application.store.adapters.in.dtos.request.CreateStoreRequest;
import br.com.bookschange.api.application.store.adapters.in.dtos.response.StoreResponse;

public interface CreateStorePortIn {
    StoreResponse create(CreateStoreRequest request);
}
