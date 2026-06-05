package br.com.bookschange.api.application.user.usecases;

import br.com.bookschange.api.application.store.ports.out.FindStorePortOut;
import br.com.bookschange.api.application.store.ports.out.SaveStorePortOut;
import br.com.bookschange.api.application.user.adapters.in.dtos.response.FindUserResponse;
import br.com.bookschange.api.application.user.mappers.UserMapper;
import br.com.bookschange.api.application.user.ports.in.InactiveActiveUserPortIn;
import br.com.bookschange.api.application.user.ports.out.FindUserPortOut;
import br.com.bookschange.api.application.user.ports.out.SaveUserPortOut;
import br.com.bookschange.api.domain.exceptions.BusinessException;
import br.com.bookschange.api.domain.exceptions.NotFoundException;
import br.com.bookschange.api.domain.models.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class InactiveActiveUseCase implements InactiveActiveUserPortIn {

    private static final String ACTIVE = "active";
    private static final String INACTIVE = "inactive";

    private final UserMapper mapper;
    private final FindUserPortOut findUserPortOut;
    private final SaveUserPortOut saveUserPortOut;
    private final FindStorePortOut findStorePortOut;
    private final SaveStorePortOut saveStorePortOut;

    @Override
    @Transactional
    public FindUserResponse inactiveActive(UUID uuid, String pathParam) {
        String action = pathParam.equals(INACTIVE) ? "Inativando usuário" : "Ativando usuário";

        log.info("{} | uuid: {}", action, uuid);

        User foundUser = findUserPortOut.findByUuid(uuid)
                .orElseThrow(() -> {
                    log.warn("Usuário não encontrado | uuid: {}", uuid);
                    return new NotFoundException("Usuário não encontrado");
                }
        );

        checkInactiveActiveParam(pathParam, foundUser);

        findStorePortOut.findByOwnerUuid(foundUser.getUuid()).ifPresent(store -> {
            store.setActive(foundUser.isActive());
            saveStorePortOut.save(store);
        });

        User user = saveUserPortOut.save(foundUser);

        action = pathParam.equals(INACTIVE) ? "Usuário inativado com sucesso" : "Usuário ativado com sucesso";

        log.info("{} | uuid: {} | status: {}", action, foundUser.getUuid(), pathParam);
        return mapper.toFindUserResponse(user);
    }

    private static void checkInactiveActiveParam(String pathParam, User foundUser) {
        if (pathParam.equalsIgnoreCase(ACTIVE)) {
            if (foundUser.isActive()) {
                log.warn("O usuário já está ativo | uuid: {} | status: {}", foundUser.getUuid(), pathParam);
                throw new BusinessException("O usuário já está ativo");
            }
            foundUser.setActive(true);
        } else if (pathParam.equalsIgnoreCase(INACTIVE)) {
            if (!foundUser.isActive()) {
                log.warn("O usuário já está inativo | uuid: {} | status: {}", foundUser.getUuid(), pathParam);
                throw new BusinessException("O usuário já está inativo");
            }
            foundUser.setActive(false);
        } else {
            log.error("Ação inválida. Use 'active' ou 'inactive' | uuid: {} | pathParam: {}", foundUser.getUuid(), pathParam);
            throw new BusinessException("Ação inválida. Use 'active' ou 'inactive'");
        }
    }
}
