package br.com.bookschange.api.application.store.usecases;

import br.com.bookschange.api.application.store.adapters.in.dtos.request.UpdateStoreRequest;
import br.com.bookschange.api.application.store.adapters.in.dtos.response.StoreResponse;
import br.com.bookschange.api.application.store.mappers.StoreMapper;
import br.com.bookschange.api.application.store.ports.in.UpdateStorePortIn;
import br.com.bookschange.api.application.store.ports.out.FindStorePortOut;
import br.com.bookschange.api.application.store.ports.out.SaveStorePortOut;
import br.com.bookschange.api.application.store.services.StoreNormalizer;
import br.com.bookschange.api.application.store.services.StoreValidator;
import br.com.bookschange.api.domain.exceptions.BusinessException;
import br.com.bookschange.api.domain.exceptions.NotFoundException;
import br.com.bookschange.api.domain.models.Store;
import br.com.bookschange.infrastructure.shared.util.PhoneUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateStoreUseCase implements UpdateStorePortIn {

    private final StoreMapper mapper;
    private final StoreValidator validator;
    private final StoreNormalizer normalizer;
    private final SaveStorePortOut saveStorePortOut;
    private final FindStorePortOut findStorePortOut;

    @Override
    public StoreResponse update(UUID uuid, UpdateStoreRequest request) {
        log.info("Buscando loja para edição | uuid: {}", uuid);

        validator.validateUpdate(uuid, request.slug());

        Store foundStore = findStorePortOut.findByUuid(uuid)
                .orElseThrow(() -> {
                    log.warn("Loja não encontrada | uuid: {}", uuid);
                    return new NotFoundException("Loja não encontrada");
                });

        mapper.updateStoreRequestToEntity(request, foundStore);

        normalizer.normalize(foundStore);

        Store updatedStore = saveStorePortOut.save(foundStore);

        log.info("Edição de loja feita com sucesso");
        return mapper.toStoreResponse(updatedStore);
    }

    private void normalizeData(Store store) {
        store.setPhone(PhoneUtil.normalize(store.getPhone()));
    }

    private void validateSlug(String slug) {
        String normalizedSlug = slug.trim().toLowerCase();
        boolean slugAlreadyExists = findStorePortOut.existsBySlug(normalizedSlug);

        if (slugAlreadyExists) {
            log.warn("Tentativa de cadastro com identificador existente");
            throw new BusinessException("Já existe uma loja cadastrada com esse identificador");
        }
    }
}
