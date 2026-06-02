package br.com.bookschange.api.application.store.mappers;

import br.com.bookschange.api.application.store.adapters.in.dtos.request.CreateStoreRequest;
import br.com.bookschange.api.application.store.adapters.in.dtos.response.StoreResponse;
import br.com.bookschange.api.domain.models.Store;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    Store createStoreRequestToEntity(CreateStoreRequest request);

    StoreResponse toStoreResponse(Store store);
}
