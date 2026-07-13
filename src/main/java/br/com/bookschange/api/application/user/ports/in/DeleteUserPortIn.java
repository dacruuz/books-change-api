package br.com.bookschange.api.application.user.ports.in;

import java.util.UUID;

public interface DeleteUserPortIn {
    void delete(UUID uuid);
}
