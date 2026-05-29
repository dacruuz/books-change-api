package br.com.bookschange.api.application.user.adapters.out.repositories;

import br.com.bookschange.api.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<User, UUID> {
    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}
