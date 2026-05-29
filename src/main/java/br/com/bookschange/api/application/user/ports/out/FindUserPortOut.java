package br.com.bookschange.api.application.user.ports.out;

import br.com.bookschange.api.domain.models.User;

import java.util.Optional;
import java.util.UUID;

public interface FindUserPortOut {
    Optional<User> findByUuid(UUID uuid);

    boolean existsByCpf(String normalizedCpf);

    boolean existsByEmail(String email);
}
