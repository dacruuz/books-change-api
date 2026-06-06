package br.com.bookschange.api.application.store.usecases;

import br.com.bookschange.api.application.store.adapters.in.dtos.response.StoreResponse;
import br.com.bookschange.api.application.store.mappers.StoreMapper;
import br.com.bookschange.api.application.store.ports.in.FindStorePortIn;
import br.com.bookschange.api.application.store.ports.out.FindStorePortOut;
import br.com.bookschange.api.domain.exceptions.BusinessException;
import br.com.bookschange.api.domain.models.Store;
import br.com.bookschange.infrastructure.shared.util.CNPJUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindStoreUseCase implements FindStorePortIn {

    private final StoreMapper mapper;
    private final FindStorePortOut findStorePortOut;

    @Override
    public StoreResponse findByUuid(UUID uuid) {
        log.info("Buscando loja | uuid: {}", uuid);

        Store store = findStorePortOut.findByUuidOrThrow(uuid);

        log.info("Loja encontrada | uuid: {} | loja: {}",
                store.getUuid(),
                store.getName());
        return mapper.toStoreResponse(store);
    }
}
