package br.com.bookschange.api.application.user.usecases;

import br.com.bookschange.api.application.user.adapters.in.dtos.response.FindUserResponse;
import br.com.bookschange.api.application.user.mappers.UserMapper;
import br.com.bookschange.api.application.user.ports.in.FindUserPortIn;
import br.com.bookschange.api.application.user.ports.out.FindUserPortOut;
import br.com.bookschange.api.domain.exceptions.NotFoundException;
import br.com.bookschange.api.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindUserUseCase implements FindUserPortIn {

    private final UserMapper mapper;
    private final FindUserPortOut findUserPortOut;

    @Override
    public FindUserResponse findByUuid(UUID uuid) {
        User foundUser = findUserPortOut.findByUuid(uuid).orElseThrow(
                () -> new NotFoundException("Usuário não encontrado")
        );
        return mapper.toFindUserResponse(foundUser);
    }
}
