package br.com.bookschange.api.application.store.usecases;

import br.com.bookschange.api.application.store.adapters.in.dtos.request.CreateStoreRequest;
import br.com.bookschange.api.application.store.adapters.in.dtos.response.StoreResponse;
import br.com.bookschange.api.application.store.mappers.StoreMapper;
import br.com.bookschange.api.application.store.ports.in.CreateStorePortIn;
import br.com.bookschange.api.application.store.ports.out.FindStorePortOut;
import br.com.bookschange.api.application.store.ports.out.SaveStorePortOut;
import br.com.bookschange.api.domain.exceptions.BusinessException;
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
    private final FindStorePortOut findStorePortOut;

    @Override
    public StoreResponse create(CreateStoreRequest request) {
        log.info("Iniciando criação de loja | cnpj: {} | e-mail: {}",
                CNPJUtil.format(request.cnpj()),
                request.commercialEmail());

        validadeDate(request);

        Store store = mapper.createStoreRequestToEntity(request);
        normalizeData(store);

        Store createdStore = saveStorePortOut.save(store);

        log.info("Loja criada com sucesso | uuid: {} | cnpj: {} | e-mail: {}",
                createdStore.getUuid(),
                CNPJUtil.format(createdStore.getCnpj()),
                createdStore.getCommercialEmail());

        return mapper.toStoreResponse(createdStore);
    }

    private void normalizeData(Store store) {
        store.setCnpj(CNPJUtil.normalize(store.getCnpj()));
        store.setPhone(PhoneUtil.normalize(store.getPhone()));
        store.setCommercialEmail(store.getCommercialEmail().trim().toLowerCase());
        store.setSlug(store.getSlug().trim().toLowerCase());
    }

    private void validadeDate(CreateStoreRequest request) {
        validateEmail(request.commercialEmail());
        validateCpnj(request.cnpj());
        validateSlug(request.slug());
    }

    private void validateSlug(String slug) {
        String normalizedSlug = slug.trim().toLowerCase();
        boolean slugAlreadyExists = findStorePortOut.existsBySlug(normalizedSlug);

        if (slugAlreadyExists) {
            log.warn("Tentativa de cadastro com identificador existente");
            throw new BusinessException("Já existe uma loja cadastrada com esse identificador");
        }
    }

    private void validateEmail(String email) {
        String normalizedEmail = email.trim().toLowerCase();
        boolean emailAlreadyExists = findStorePortOut.existsByEmail(normalizedEmail);

        if (emailAlreadyExists) {
            log.warn("Tentativa de cadastro com e-mail existente");
            throw new BusinessException("Já existe uma loja cadastrada com esse e-mail");
        }
    }

    private void validateCpnj(String cnpj) {
        String normalizedCnpj = CNPJUtil.normalize(cnpj);
        boolean cnpjAlreadyExists = findStorePortOut.existsByCnpj(normalizedCnpj);

        if (cnpjAlreadyExists) {
            log.warn("Tentativa de cadastro com CNPJ existente");
            throw new BusinessException("Já existe uma loja cadastrada com esse CNPJ");
        }
    }
}
