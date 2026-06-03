package br.com.bookschange.api.application.address.ports.in;

import br.com.bookschange.api.application.address.adapters.in.dtos.request.UpdateAddressRequest;
import br.com.bookschange.api.application.address.adapters.in.dtos.response.AddressResponse;

import java.util.UUID;

public interface UpdateAddressPortIn {
    AddressResponse update(UUID uuid, UpdateAddressRequest request);
}
