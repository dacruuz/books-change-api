package br.com.bookschange.api.application.user.ports.out;

import br.com.bookschange.api.domain.models.User;

public interface CreateUserPortOut {
    User create(User user);
}
