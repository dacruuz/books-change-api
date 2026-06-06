package br.com.bookschange.api.application.address.adapters.out;

import br.com.bookschange.api.application.address.adapters.out.repositories.AddressJpaRepository;
import br.com.bookschange.api.application.address.ports.out.FindAddressPortOut;
import br.com.bookschange.api.domain.exceptions.NotFoundException;
import br.com.bookschange.api.domain.models.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindAddressAdapter implements FindAddressPortOut {

    private final AddressJpaRepository repository;

    @Override
    public Optional<Address> findByUuid(UUID uuid) {
        return repository.findById(uuid);
    }

    @Override
    public Address findByUuidOrThrow(UUID uuid) {
        return repository.findById(uuid).orElseThrow(() -> {
            log.warn("Endereço não encontrado | uuid: {}", uuid);
            return new NotFoundException("Endereço não encontrado");
        });
    }
}
