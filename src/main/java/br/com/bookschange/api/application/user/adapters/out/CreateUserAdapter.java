package br.com.bookschange.api.application.user.adapters.out;

import br.com.bookschange.api.application.user.adapters.out.repositories.UserJpaRepository;
import br.com.bookschange.api.application.user.ports.out.CreateUserPortOut;
import br.com.bookschange.api.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserAdapter implements CreateUserPortOut {

    private final UserJpaRepository repository;

    @Override
    public User create(User user) {
        return repository.save(user);
    }
}
