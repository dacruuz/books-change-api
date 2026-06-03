package br.com.bookschange.api.application.address.ports.in;

import java.util.UUID;

public interface DeleteAddressPortIn {
    void delete(UUID uuid);
}
