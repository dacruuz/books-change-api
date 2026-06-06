package br.com.bookschange.api.application.store.mappers;

import br.com.bookschange.api.application.store.adapters.in.dtos.request.CreateStoreRequest;
import br.com.bookschange.api.application.store.adapters.in.dtos.request.UpdateStoreRequest;
import br.com.bookschange.api.application.store.adapters.in.dtos.response.StoreResponse;
import br.com.bookschange.api.domain.models.Store;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    Store createStoreRequestToEntity(CreateStoreRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStoreRequestToEntity(UpdateStoreRequest request, @MappingTarget Store store);

    @Mapping(target = "ownerUuid", source = "owner.uuid")
    StoreResponse toStoreResponse(Store store);
}
