package br.com.bookschange.api.application.store.usecases;

import br.com.bookschange.api.application.store.ports.in.DeleteStorePortIn;
import br.com.bookschange.api.application.store.ports.out.DeleteStorePortOut;
import br.com.bookschange.api.application.store.ports.out.FindStorePortOut;
import br.com.bookschange.api.application.user.ports.out.FindUserPortOut;
import br.com.bookschange.api.application.user.ports.out.SaveUserPortOut;
import br.com.bookschange.api.domain.enums.UserType;
import br.com.bookschange.api.domain.exceptions.NotFoundException;
import br.com.bookschange.api.domain.models.Store;
import br.com.bookschange.api.domain.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteStoreUseCase implements DeleteStorePortIn {

    private final FindStorePortOut findStorePortOut;
    private final DeleteStorePortOut deleteStorePortOut;
    private final FindUserPortOut findUserPortOut;
    private final SaveUserPortOut saveUserPortOut;

    @Override
    public void delete(UUID storeUuid, UUID ownerUuid) {
        log.info("Excluindo loja | uuid: {}", storeUuid);

        Store store = findStorePortOut.findByUuidOrThrow(storeUuid);

        User owner = findUserPortOut.findByUuidOrThrow(ownerUuid);
        owner.setUserType(UserType.DEFAULT);

        saveUserPortOut.save(owner);
        deleteStorePortOut.delete(store);
    }
}
