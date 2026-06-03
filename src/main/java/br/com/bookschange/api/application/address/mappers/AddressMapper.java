package br.com.bookschange.api.application.address.mappers;

import br.com.bookschange.api.application.address.adapters.in.dtos.request.CreateAddressRequest;
import br.com.bookschange.api.application.address.adapters.in.dtos.response.AddressResponse;
import br.com.bookschange.api.domain.models.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address createAddressRequestToEntity(CreateAddressRequest request);

    AddressResponse toResponse(Address address);
}
