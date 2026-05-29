package br.com.bookschange.api.application.user.ports.in;

import br.com.bookschange.api.application.user.adapters.in.dtos.request.CreateUserRequest;
import br.com.bookschange.api.application.user.adapters.in.dtos.response.CreateUserResponse;
import br.com.bookschange.api.domain.enums.UserType;

public interface CreateUserPortIn {
    CreateUserResponse create(String userType, CreateUserRequest request);
}
