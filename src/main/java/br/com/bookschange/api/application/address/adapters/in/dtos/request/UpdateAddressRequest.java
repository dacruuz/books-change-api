package br.com.bookschange.api.application.address.adapters.in.dtos.request;

public record UpdateAddressRequest(
        String zipCode,
        String street,
        String number,
        String state,
        String country,
        String city,
        String complement,
        String neighborhood
) {
}
