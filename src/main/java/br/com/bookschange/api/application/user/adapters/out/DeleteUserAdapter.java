package br.com.bookschange.api.application.user.adapters.out;

import br.com.bookschange.api.application.user.adapters.out.repositories.UserJpaRepository;
import br.com.bookschange.api.application.user.ports.out.DeleteUserPortOut;
import br.com.bookschange.api.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteUserAdapter implements DeleteUserPortOut {

    private final UserJpaRepository repository;

    @Override
    public void delete(User user) {
        repository.delete(user);
    }
}
