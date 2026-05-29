package br.com.bookschange.api.application.user.adapters.out;

import br.com.bookschange.api.application.user.adapters.out.repositories.UserJpaRepository;
import br.com.bookschange.api.application.user.ports.out.UpdateUserPorOut;
import br.com.bookschange.api.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateUserAdapter implements UpdateUserPorOut {

    private final UserJpaRepository repository;

    @Override
    public User update(User user) {
        return repository.save(user);
    }
}
