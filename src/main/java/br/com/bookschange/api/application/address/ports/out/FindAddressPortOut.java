package br.com.bookschange.api.application.address.ports.out;

import br.com.bookschange.api.domain.models.Address;

import java.util.Optional;
import java.util.UUID;

public interface FindAddressPortOut {
    Optional<Address> findByUuid(UUID uuid);
}
