package br.com.bookschange.api.application.user.ports.in;

import br.com.bookschange.api.application.user.adapters.in.dtos.response.UserResponse;

import java.util.UUID;

public interface FindUserPortIn {
    UserResponse findByUuid(UUID uuid);
}
