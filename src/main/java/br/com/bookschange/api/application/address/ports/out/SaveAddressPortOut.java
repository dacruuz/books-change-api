package br.com.bookschange.api.application.address.ports.out;

import br.com.bookschange.api.domain.models.Address;

public interface SaveAddressPortOut {
    Address create(Address address);
}
