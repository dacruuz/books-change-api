package br.com.bookschange.api.application.address.adapters.out;

import br.com.bookschange.api.application.address.adapters.out.repositories.AddressJpaRepository;
import br.com.bookschange.api.application.address.ports.out.SaveAddressPortOut;
import br.com.bookschange.api.domain.models.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveAddressAdapter implements SaveAddressPortOut {

    private final AddressJpaRepository repository;

    @Override
    public Address save(Address address) {
        return repository.save(address);
    }
}
