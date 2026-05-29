package br.com.bookschange.api.application.user.ports.in;

import br.com.bookschange.api.application.user.adapters.in.dtos.response.FindUserResponse;

import java.util.UUID;

public interface FindUserPortIn {
    FindUserResponse findByUuid(UUID uuid);
}
