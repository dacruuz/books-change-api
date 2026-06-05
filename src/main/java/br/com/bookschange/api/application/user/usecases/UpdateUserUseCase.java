package br.com.bookschange.api.application.user.usecases;

import br.com.bookschange.api.application.user.adapters.in.dtos.request.UpdateUserRequest;
import br.com.bookschange.api.application.user.adapters.in.dtos.response.FindUserResponse;
import br.com.bookschange.api.application.user.mappers.UserMapper;
import br.com.bookschange.api.application.user.ports.in.UpdateUserPortIn;
import br.com.bookschange.api.application.user.ports.out.FindUserPortOut;
import br.com.bookschange.api.application.user.ports.out.SaveUserPortOut;
import br.com.bookschange.api.domain.exceptions.NotFoundException;
import br.com.bookschange.api.domain.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateUserUseCase implements UpdateUserPortIn {

    private final UserMapper mapper;
    private final FindUserPortOut findUserPortOut;
    private final SaveUserPortOut saveUserPortOut;

    @Override
    public FindUserResponse update(UUID uuid, UpdateUserRequest request) {
        log.info("Buscando usuário para edição | uuid: {}", uuid);

        User foundUser = findUserPortOut.findByUuid(uuid)
                .orElseThrow(() -> {
                    log.info("Usuário não encontrado | uuid: {}", uuid);
                    return new NotFoundException("Usuário não encontrado.");
                }
        );

        mapper.updateUserRequestToEntity(request, foundUser);

        User updatedUser = saveUserPortOut.save(foundUser);

        log.info("Edição de usuário feita com sucesso");
        return mapper.toFindUserResponse(updatedUser);
    }
}
