package br.com.bookschange.api.application.store.usecases;

import br.com.bookschange.api.application.store.adapters.in.dtos.request.CreateStoreRequest;
import br.com.bookschange.api.application.store.adapters.in.dtos.response.StoreResponse;
import br.com.bookschange.api.application.store.mappers.StoreMapper;
import br.com.bookschange.api.application.store.ports.in.CreateStorePortIn;
import br.com.bookschange.api.application.store.ports.out.SaveStorePortOut;
import br.com.bookschange.api.domain.models.Store;
import br.com.bookschange.infrastructure.shared.util.CNPJUtil;
import br.com.bookschange.infrastructure.shared.util.PhoneUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateStoreUseCase implements CreateStorePortIn {

    private final StoreMapper mapper;
    private final SaveStorePortOut saveStorePortOut;

    @Override
    public StoreResponse create(CreateStoreRequest request) {
        log.info("Iniciando criação de loja | cnpj: {} | e-mail: {}",
                CNPJUtil.format(request.cnpj()),
                request.commercialEmail());

        Store store = mapper.createStoreRequestToEntity(request);
        normalizeData(store);

        Store createdStore = saveStorePortOut.save(store);

        log.info("Loja criada com sucesso | uuid: {} | cnpj: {} | e-mail: {}",
                createdStore.getUuid(),
                CNPJUtil.format(createdStore.getCnpj()),
                createdStore.getCommercialEmail());

        return mapper.toCreateStoreResponse(createdStore);
    }

    private void normalizeData(Store store) {
        store.setCnpj(CNPJUtil.normalize(store.getCnpj()));
        store.setPhone(PhoneUtil.normalize(store.getPhone()));
    }
}
