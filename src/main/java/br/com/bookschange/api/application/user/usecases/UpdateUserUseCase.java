package br.com.bookschange.api.application.user.usecases;

import br.com.bookschange.api.application.user.adapters.in.dtos.request.UpdateUserRequest;
import br.com.bookschange.api.application.user.adapters.in.dtos.response.UserResponse;
import br.com.bookschange.api.application.user.mappers.UserMapper;
import br.com.bookschange.api.application.user.ports.in.UpdateUserPortIn;
import br.com.bookschange.api.application.user.ports.out.FindUserPortOut;
import br.com.bookschange.api.application.user.ports.out.SaveUserPortOut;
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
    public UserResponse update(UUID uuid, UpdateUserRequest request) {
        log.info("Buscando usuário para edição | uuid: {}", uuid);

        User user = findUserPortOut.findByUuidOrThrow(uuid);

        mapper.updateUserRequestToEntity(request, user);

        User updatedUser = saveUserPortOut.save(user);

        log.info("Edição de usuário feita com sucesso");
        return mapper.toUserResponse(updatedUser);
    }
}
