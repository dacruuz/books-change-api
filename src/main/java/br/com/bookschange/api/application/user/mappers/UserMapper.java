package br.com.bookschange.api.application.user.mappers;

import br.com.bookschange.api.application.user.adapters.in.dtos.request.CreateUserRequest;
import br.com.bookschange.api.application.user.adapters.in.dtos.response.CreateUserResponse;
import br.com.bookschange.api.application.user.adapters.in.dtos.response.FindUserResponse;
import br.com.bookschange.api.domain.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User createUserRequestToEntity(CreateUserRequest request);
    CreateUserResponse toCreateUserResponse(User user);

    FindUserResponse toFindUserResponse(User user);
}
