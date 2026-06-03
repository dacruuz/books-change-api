package br.com.bookschange.api.application.store.adapters.in.dtos.request;

public record UpdateStoreRequest(
        String name,
        String phone,
        String slug,
        String description
) {
}
