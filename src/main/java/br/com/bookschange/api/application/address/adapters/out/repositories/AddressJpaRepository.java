package br.com.bookschange.api.application.address.adapters.out.repositories;

import br.com.bookschange.api.domain.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressJpaRepository extends JpaRepository<Address, UUID> {
}
