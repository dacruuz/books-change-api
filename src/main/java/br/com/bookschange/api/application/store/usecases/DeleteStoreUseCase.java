package br.com.bookschange.api.application.store.usecases;

import br.com.bookschange.api.application.store.ports.in.DeleteStorePortIn;
import br.com.bookschange.api.application.store.ports.out.DeleteStorePortOut;
import br.com.bookschange.api.application.store.ports.out.FindStorePortOut;
import br.com.bookschange.api.domain.exceptions.NotFoundException;
import br.com.bookschange.api.domain.models.Store;
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

    @Override
    public void delete(UUID uuid) {
        log.info("Excluindo loja | uuid: {}", uuid);

        Store foundStore = findStorePortOut.findByUuid(uuid)
                .orElseThrow(() -> {
                    log.warn("Loja não encontrada | uuid: {}", uuid);
                    return new NotFoundException("Loja não encontrada");
                });

        deleteStorePortOut.delete(foundStore);
    }
}
