package br.com.bookschange.api.application.address.services;

import br.com.bookschange.api.domain.models.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressNormalizer {

    public void normalize(Address address) {
        if (address.getZipCode() != null) {
            address.setZipCode(address.getZipCode().replaceAll("\\D", ""));
        }

        if (address.getStreet() != null) {
            address.setStreet(address.getStreet().toUpperCase());
        }

        if (address.getState() != null) {
            address.setState(address.getState().toUpperCase());
        }

        if (address.getCountry() != null) {
            address.setCountry(address.getCountry().toUpperCase());
        }

        if (address.getCity() != null) {
            address.setCity(address.getCity().toUpperCase());
        }

        if (address.getComplement() != null) {
            address.setComplement(address.getComplement().toUpperCase());
        }

        if (address.getNeighborhood() != null) {
            address.setNeighborhood(address.getNeighborhood().toUpperCase());
        }
    }
}
