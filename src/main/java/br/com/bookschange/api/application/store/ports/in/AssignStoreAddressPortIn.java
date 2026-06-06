package br.com.bookschange.api.application.store.ports.in;

import java.util.UUID;

public interface AssignStoreAddressPortIn {
    void assign(UUID storeUuid, UUID addressUuid);
}
