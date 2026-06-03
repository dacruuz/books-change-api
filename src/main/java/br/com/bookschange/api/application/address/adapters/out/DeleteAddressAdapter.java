package br.com.bookschange.api.application.address.adapters.out;

import br.com.bookschange.api.application.address.adapters.out.repositories.AddressJpaRepository;
import br.com.bookschange.api.application.address.ports.out.DeleteAddressPortOut;
import br.com.bookschange.api.domain.models.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteAddressAdapter implements DeleteAddressPortOut {

    private final AddressJpaRepository repository;

    @Override
    public void delete(Address address) {
        repository.delete(address);
    }
}
