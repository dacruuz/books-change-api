package br.com.bookschange.api.application.user.mappers;

import br.com.bookschange.api.application.user.adapters.in.dtos.request.CreateUserRequest;
import br.com.bookschange.api.application.user.adapters.in.dtos.request.UpdateUserRequest;
import br.com.bookschange.api.application.user.adapters.in.dtos.response.UserResponse;
import br.com.bookschange.api.domain.models.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User createUserRequestToEntity(CreateUserRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserRequestToEntity(UpdateUserRequest request, @MappingTarget User user);

    UserResponse entityToUserResponse(User user);
}
