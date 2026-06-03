package br.com.bookschange.api.application.store.ports.in;

import java.util.UUID;

public interface DeleteStorePortIn {
    void delete(UUID uuid);
}
