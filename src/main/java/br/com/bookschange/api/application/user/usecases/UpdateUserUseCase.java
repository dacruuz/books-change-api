package br.com.bookschange.api.application.user.usecases;

import br.com.bookschange.api.application.user.adapters.in.dtos.request.UpdateUserRequest;
import br.com.bookschange.api.application.user.adapters.in.dtos.response.FindUserResponse;
import br.com.bookschange.api.application.user.mappers.UserMapper;
import br.com.bookschange.api.application.user.ports.in.UpdateUserPortIn;
import br.com.bookschange.api.application.user.ports.out.FindUserPortOut;
import br.com.bookschange.api.application.user.ports.out.UpdateUserPorOut;
import br.com.bookschange.api.domain.exceptions.NotFoundException;
import br.com.bookschange.api.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase implements UpdateUserPortIn {

    private final UserMapper mapper;
    private final FindUserPortOut findUserPortOut;
    private final UpdateUserPorOut updateUserPorOut;

    @Override
    public FindUserResponse update(UUID uuid, UpdateUserRequest request) {
        User foundUser = findUserPortOut.findByUuid(uuid).orElseThrow(
                () -> new NotFoundException("Usuário não encontrado.")
        );

        mapper.updateUserRequestToEntity(request, foundUser);

        User updatedUser = updateUserPorOut.update(foundUser);
        return mapper.toFindUserResponse(updatedUser);
    }
}
