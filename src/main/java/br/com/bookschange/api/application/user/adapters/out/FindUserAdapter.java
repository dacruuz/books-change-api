package br.com.bookschange.api.application.user.adapters.out;

import br.com.bookschange.api.application.user.adapters.out.repositories.UserJpaRepository;
import br.com.bookschange.api.application.user.ports.out.FindUserPortOut;
import br.com.bookschange.api.domain.exceptions.NotFoundException;
import br.com.bookschange.api.domain.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindUserAdapter implements FindUserPortOut {

    private final UserJpaRepository repository;

    @Override
    public Optional<User> findByUuid(UUID uuid) {
        return repository.findById(uuid);
    }

    @Override
    public User findByUuidOrThrow(UUID uuid) {
        return repository.findById(uuid)
                .orElseThrow(() -> {
                    log.info("Usuário não encontrado | uuid: {}", uuid);
                    return new NotFoundException("Usuário não encontrado");
                }
        );
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
