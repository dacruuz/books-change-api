package br.com.bookschange.api.application.address.usecases;

import br.com.bookschange.api.application.address.ports.in.DeleteAddressPortIn;
import br.com.bookschange.api.application.address.ports.out.DeleteAddressPortOut;
import br.com.bookschange.api.application.address.ports.out.FindAddressPortOut;
import br.com.bookschange.api.domain.exceptions.NotFoundException;
import br.com.bookschange.api.domain.models.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteAddressUseCase implements DeleteAddressPortIn {

    private final FindAddressPortOut findAddressPortOut;
    private final DeleteAddressPortOut deleteAddressPortOut;

    @Override
    public void delete(UUID uuid) {
        log.info("Deletando endereço | uuid: {}", uuid);

        Address foundAddress = findAddressPortOut.findByUuidOrThrow(uuid);

        deleteAddressPortOut.delete(foundAddress);
        log.info("Endereço deletado com sucesso");
    }
}
