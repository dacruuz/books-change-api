package br.com.bookschange.api.application.store.adapters.in.dtos.response;

public record StoreResponse(
        String name,
        String cnpj,
        String commercialEmail,
        String phone,
        String slug,
        String description
) {
}
