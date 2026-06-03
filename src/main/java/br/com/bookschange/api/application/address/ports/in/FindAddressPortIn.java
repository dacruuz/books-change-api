package br.com.bookschange.api.application.address.ports.in;

import br.com.bookschange.api.application.address.adapters.in.dtos.response.AddressResponse;

import java.util.UUID;

public interface FindAddressPortIn {
    AddressResponse findByUuid(UUID uuid);
}
