package br.com.bookschange.api.application.user.ports.in;

import br.com.bookschange.api.application.user.adapters.in.dtos.request.UpdateUserRequest;
import br.com.bookschange.api.application.user.adapters.in.dtos.response.UserResponse;

import java.util.UUID;

public interface UpdateUserPortIn {
    UserResponse update(UUID uuid, UpdateUserRequest request);
}
