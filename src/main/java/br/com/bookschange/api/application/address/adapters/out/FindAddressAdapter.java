package br.com.bookschange.api.application.address.adapters.out;

import br.com.bookschange.api.application.address.adapters.out.repositories.AddressJpaRepository;
import br.com.bookschange.api.application.address.ports.out.FindAddressPortOut;
import br.com.bookschange.api.domain.models.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindAddressAdapter implements FindAddressPortOut {

    private final AddressJpaRepository repository;

    @Override
    public Optional<Address> findByUuid(UUID uuid) {
        return repository.findById(uuid);
    }
}
