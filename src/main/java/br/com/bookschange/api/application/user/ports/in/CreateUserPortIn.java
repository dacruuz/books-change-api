package br.com.bookschange.api.application.user.ports.in;

import br.com.bookschange.api.application.user.adapters.in.dtos.request.CreateUserRequest;
import br.com.bookschange.api.application.user.adapters.in.dtos.response.UserResponse;

public interface CreateUserPortIn {
    UserResponse create(String userType, CreateUserRequest request);
}
