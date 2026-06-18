package br.com.bookschange.api.application.user.usecases;

import br.com.bookschange.api.application.user.ports.in.DeleteUserPortIn;
import br.com.bookschange.api.application.user.ports.out.DeleteUserPortOut;
import br.com.bookschange.api.application.user.ports.out.FindUserPortOut;
import br.com.bookschange.api.domain.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteUserUseCase implements DeleteUserPortIn {

    private final DeleteUserPortOut deleteUserPortOut;
    private final FindUserPortOut findUserPortOut;

    @Override
    public void delete(UUID uuid) {
        log.info("Iniciando exclusão de usuário | uuid: {}", uuid);

        User user = findUserPortOut.findByUuidOrThrow(uuid);
        deleteUserPortOut.delete(user);

        log.info("Usuário excluído com sucesso");
    }
}
