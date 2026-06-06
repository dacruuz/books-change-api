package br.com.bookschange.api.application.store.usecases;

import br.com.bookschange.api.application.address.ports.out.FindAddressPortOut;
import br.com.bookschange.api.application.store.ports.in.AssignStoreAddressPortIn;
import br.com.bookschange.api.application.store.ports.out.FindStorePortOut;
import br.com.bookschange.api.application.store.ports.out.SaveStorePortOut;
import br.com.bookschange.api.domain.models.Address;
import br.com.bookschange.api.domain.models.Store;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssignStoreAddressUseCase implements AssignStoreAddressPortIn {

    private final FindStorePortOut findStorePortOut;
    private final SaveStorePortOut saveStorePortOut;
    private final FindAddressPortOut findAddressPortOut;

    @Override
    @Transactional
    public void assign(UUID storeUuid, UUID addressUuid) {
        log.info("Associando a loja com o endereço | storeUuid: {} | addressUuid: {}",
                storeUuid, addressUuid
        );

        Store store = findStorePortOut.findByUuidOrThrow(storeUuid);
        Address address = findAddressPortOut.findByUuidOrThrow(addressUuid);

        store.setAddress(address); // assign or update store address

        log.info("Loja associada com sucesso | storeUuid: {} | addressUuid: {}",
                storeUuid, addressUuid
        );
        saveStorePortOut.save(store);
    }
}
