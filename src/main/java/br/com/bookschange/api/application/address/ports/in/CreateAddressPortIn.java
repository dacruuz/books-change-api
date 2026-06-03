package br.com.bookschange.api.application.address.ports.in;

import br.com.bookschange.api.application.address.adapters.in.dtos.request.CreateAddressRequest;
import br.com.bookschange.api.application.address.adapters.in.dtos.response.AddressResponse;

public interface CreateAddressPortIn {
    AddressResponse create(CreateAddressRequest request);
}
