package br.com.bookschange.api.application.user.usecases;

import br.com.bookschange.api.application.user.adapters.in.dtos.response.FindUserResponse;
import br.com.bookschange.api.application.user.mappers.UserMapper;
import br.com.bookschange.api.application.user.ports.in.InactiveActiveUserPortIn;
import br.com.bookschange.api.application.user.ports.out.FindUserPortOut;
import br.com.bookschange.api.application.user.ports.out.UpdateUserPorOut;
import br.com.bookschange.api.domain.exceptions.BusinessException;
import br.com.bookschange.api.domain.exceptions.NotFoundException;
import br.com.bookschange.api.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InactiveActiveUseCase implements InactiveActiveUserPortIn {

    private static final String ACTIVE = "active";
    private static final String INACTIVE = "inactive";

    private final UserMapper mapper;
    private final FindUserPortOut findUserPortOut;
    private final UpdateUserPorOut updateUserPorOut;

    @Override
    public FindUserResponse inactiveActive(UUID uuid, String pathParam) {
        User foundUser = findUserPortOut.findByUuid(uuid).orElseThrow(
                () -> new NotFoundException("Usuário não encontrado")
        );

        checkInactiveActiveParam(pathParam, foundUser);

        User user = updateUserPorOut.update(foundUser);

        return mapper.toFindUserResponse(user);
    }

    private static void checkInactiveActiveParam(String pathParam, User foundUser) {
        if (pathParam.equalsIgnoreCase(ACTIVE)) {
            if (foundUser.isActive()) {
                throw new BusinessException("O usuário já está ativo");
            }
            foundUser.setActive(true);
        } else if (pathParam.equalsIgnoreCase(INACTIVE)) {
            if (!foundUser.isActive()) {
                throw new BusinessException("O usuário já está inativo");
            }
            foundUser.setActive(false);
        } else {
            throw new BusinessException("Ação inválida. Use 'active' ou 'inactive'");
        }
    }
}
