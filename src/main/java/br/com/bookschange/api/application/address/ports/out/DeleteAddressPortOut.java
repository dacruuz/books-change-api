package br.com.bookschange.api.application.address.ports.out;

import br.com.bookschange.api.domain.models.Address;

public interface DeleteAddressPortOut {
    void delete(Address address);
}
