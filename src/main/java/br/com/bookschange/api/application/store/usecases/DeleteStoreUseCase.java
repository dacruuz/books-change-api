package br.com.bookschange.api.application.store.usecases;

import br.com.bookschange.api.application.address.ports.out.DeleteAddressPortOut;
import br.com.bookschange.api.application.store.ports.in.DeleteStorePortIn;
import br.com.bookschange.api.application.store.ports.out.DeleteStorePortOut;
import br.com.bookschange.api.application.store.ports.out.FindStorePortOut;
import br.com.bookschange.api.application.store.services.StoreDeletionService;
import br.com.bookschange.api.application.user.ports.out.FindUserPortOut;
import br.com.bookschange.api.application.user.ports.out.SaveUserPortOut;
import br.com.bookschange.api.domain.enums.UserType;
import br.com.bookschange.api.domain.models.Address;
import br.com.bookschange.api.domain.models.Store;
import br.com.bookschange.api.domain.models.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteStoreUseCase implements DeleteStorePortIn {

    private final FindStorePortOut findStorePortOut;
    private final StoreDeletionService storeDeletionService;

    @Override
    @Transactional
    public void delete(UUID storeUuid) {
        Store store = findStorePortOut.findByUuidOrThrow(storeUuid);

        storeDeletionService.delete(store);
    }
}
